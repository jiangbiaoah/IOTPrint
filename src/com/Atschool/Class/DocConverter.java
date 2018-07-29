package com.Atschool.Class;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.Atschool.JavaBean.Configuration;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 格式转换:filePath为重命名后的文件名
 * DocConventer d=new DocCoventer(filePath);
 * d.conver();
 */
public class DocConverter {
	private static final int environment = 1;// 环境 1：windows 2:linux
	private String fileString; // 等待转换的文件的路径 (只涉及pdf2swf路径问题)
	//private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置
	private String fileName; // 文件名（不含后缀）
	private String tempPath; // 文档转码暂存位置
	private File pdfFile;
	private File swfFile;
	private File docFile;

	public DocConverter(String strFilePath) {
		System.out.println("创建DocConverter对象");
		ini(strFilePath);
	}

	/**
	 * 初始化
	 * @param strFilePath
	 */
	private void ini(String strFilePath) {
		this.fileString = strFilePath;
		fileName = fileString.substring(0, fileString.lastIndexOf("."));
		//tempPath = fileString.substring(0, fileString.lastIndexOf("/")) + "/temp";
		tempPath = Configuration.converterTempPath;
		docFile = new File(fileString);
		System.out.println("docFile:fileString="+fileString);
		pdfFile = new File(tempPath);
		if (!pdfFile.exists()) {
			pdfFile.mkdirs();
		}
		pdfFile = new File(
				tempPath + fileString.substring(fileString.lastIndexOf("/"), fileString.lastIndexOf(".")) + ".pdf");
		swfFile = new File(
				tempPath + fileString.substring(fileString.lastIndexOf("/"), fileString.lastIndexOf(".")) + ".swf");
		LogInfo.output("swf的绝对路径为：" + swfFile.getAbsolutePath());
	}

	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private void doc2pdf() throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, pdfFile);
					// close the connection
					connection.disconnect();
					LogInfo.output("pdf转换成功，PDF输出：" + pdfFile.getPath());
				} catch (java.net.ConnectException e) {
					LogInfo.output("swf转换器异常，openoffice服务未启动！");
					e.printStackTrace();
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					LogInfo.output("swf转换器异常，读取转换文件失败");
					e.printStackTrace();
					throw e;
				} catch (Exception e) {
					LogInfo.output("pdf转换失败");
					e.printStackTrace();
					throw e;
				}
			} else {
				LogInfo.output("已经转换为pdf，不需要再进行转化");
			}
		} else {
			LogInfo.output("swf转换器异常，需要转换的文档不存在，无法转换");
		}
	}

	/**
	 * pdf转换成 swf
	 */
	@SuppressWarnings("unused")
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (environment == 1) {// windows环境处理
					try {
						// 参考：命令行方式转换方法 C:\SWFTools\pdf2swf.exe Paper3.pdf -o
						// Paper3.swf
						Process p = r.exec(Configuration.pdf2swfexePath + " " + pdfFile.getPath() + " -o "
								+ swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						System.err.println("****swf转换成功，文件输出：" + swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							//pdfFile.delete();
						}

					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2) {// linux环境处理
					try {
						Process p = r.exec("pdf2swf " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swf转换成功，文件输出：" + swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							// pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				LogInfo.output("pdf不存在,无法转换");
			}
		} else {
			LogInfo.output("swf已经存在不需要转换");
		}
	}

	/**
	 * ?????????????????????
	 */
	static String loadStream(InputStream in) throws IOException {

		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();

		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}

		return buffer.toString();
	}

	/**
	 * 转换主方法
	 */
	@SuppressWarnings("unused")
	public boolean conver() {

		if (swfFile.exists()) {
			LogInfo.output("swf转换器开始工作，该文件已经转换为swf");
			return true;
		}

		if (environment == 1) {
			LogInfo.output("swf转换器开始工作，当前设置运行环境windows");
		} else {
			LogInfo.output("swf转换器开始工作，当前设置运行环境linux");
		}
		try {
			doc2pdf();
			pdf2swf();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (swfFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回swf文件路径
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			//System.out.println("replace前："+tempString);
			tempString = tempString.replaceAll("\\\\", "/");
			//System.out.println("replace后："+tempString);
			return tempString;
		} else {
			return "";
		}
	}
	
	/**
	 * 返回pdf文件路径
	 * 
	 * @param s
	 */
	public String getpdfPath() {
		if (pdfFile.exists()) {
			String tempString = pdfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}
	}
	

	/**
	 * 设置输出路径 ?????????????????
	 */
//	public void setOutputPath(String outputPath) {
//		this.outputPath = outputPath;
//		if (!outputPath.equals("")) {
//			String realName = fileName.substring(fileName.lastIndexOf("/"), fileName.lastIndexOf("."));
//			if (outputPath.charAt(outputPath.length()) == '/') {
//				swfFile = new File(outputPath + realName + ".swf");
//			} else {
//				swfFile = new File(outputPath + realName + ".swf");
//			}
//		}
//	}

}