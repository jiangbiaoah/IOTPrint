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
 * ��ʽת��:filePathΪ����������ļ���
 * DocConventer d=new DocCoventer(filePath);
 * d.conver();
 */
public class DocConverter {
	private static final int environment = 1;// ���� 1��windows 2:linux
	private String fileString; // �ȴ�ת�����ļ���·�� (ֻ�漰pdf2swf·������)
	//private String outputPath = "";// ����·�� ����������þ������Ĭ�ϵ�λ��
	private String fileName; // �ļ�����������׺��
	private String tempPath; // �ĵ�ת���ݴ�λ��
	private File pdfFile;
	private File swfFile;
	private File docFile;

	public DocConverter(String strFilePath) {
		System.out.println("����DocConverter����");
		ini(strFilePath);
	}

	/**
	 * ��ʼ��
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
		LogInfo.output("swf�ľ���·��Ϊ��" + swfFile.getAbsolutePath());
	}

	/**
	 * תΪPDF
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
					LogInfo.output("pdfת���ɹ���PDF�����" + pdfFile.getPath());
				} catch (java.net.ConnectException e) {
					LogInfo.output("swfת�����쳣��openoffice����δ������");
					e.printStackTrace();
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					LogInfo.output("swfת�����쳣����ȡת���ļ�ʧ��");
					e.printStackTrace();
					throw e;
				} catch (Exception e) {
					LogInfo.output("pdfת��ʧ��");
					e.printStackTrace();
					throw e;
				}
			} else {
				LogInfo.output("�Ѿ�ת��Ϊpdf������Ҫ�ٽ���ת��");
			}
		} else {
			LogInfo.output("swfת�����쳣����Ҫת�����ĵ������ڣ��޷�ת��");
		}
	}

	/**
	 * pdfת���� swf
	 */
	@SuppressWarnings("unused")
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (environment == 1) {// windows��������
					try {
						// �ο��������з�ʽת������ C:\SWFTools\pdf2swf.exe Paper3.pdf -o
						// Paper3.swf
						Process p = r.exec(Configuration.pdf2swfexePath + " " + pdfFile.getPath() + " -o "
								+ swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						System.err.println("****swfת���ɹ����ļ������" + swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							//pdfFile.delete();
						}

					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2) {// linux��������
					try {
						Process p = r.exec("pdf2swf " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swfת���ɹ����ļ������" + swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							// pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				LogInfo.output("pdf������,�޷�ת��");
			}
		} else {
			LogInfo.output("swf�Ѿ����ڲ���Ҫת��");
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
	 * ת��������
	 */
	@SuppressWarnings("unused")
	public boolean conver() {

		if (swfFile.exists()) {
			LogInfo.output("swfת������ʼ���������ļ��Ѿ�ת��Ϊswf");
			return true;
		}

		if (environment == 1) {
			LogInfo.output("swfת������ʼ��������ǰ�������л���windows");
		} else {
			LogInfo.output("swfת������ʼ��������ǰ�������л���linux");
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
	 * ����swf�ļ�·��
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			//System.out.println("replaceǰ��"+tempString);
			tempString = tempString.replaceAll("\\\\", "/");
			//System.out.println("replace��"+tempString);
			return tempString;
		} else {
			return "";
		}
	}
	
	/**
	 * ����pdf�ļ�·��
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
	 * �������·�� ?????????????????
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