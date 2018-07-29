package com.Atschool.Class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import com.itextpdf.text.pdf.PdfReader;

public class FileOperate {
	private String filePath=null;
	private String fileName=null;
	private String postfix=null;
	private int pages=0;
	private FileInputStream input=null;
	private FileOutputStream output=null;
	
	public FileOperate(String filePath) {
		ini(filePath);
	}
	private void ini(String filePath){
		this.filePath=filePath;
		this.postfix=filePath.substring(filePath.lastIndexOf("."));//»ñÈ¡ÎÄ¼þºó×ºÃû
	}
	/**
	 * ¡¾ÎÄ¼þÖØÃüÃû¡¿
	 * @param srcFilePath Ô´ÎÄ¼þµÄÂ·¾¶
	 */
	public void renameFile(){
		File oldFile=new File(filePath);
		String oldFilePath=oldFile.getAbsolutePath();
		//»ñÈ¡ÏµÍ³µ±Ç°Ê±¼ä
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time=sdf.format(new Date());
		//¸ü¸ÄÃû³ÆºóÎÄ¼þµÄÂ·¾¶
		this.filePath=oldFilePath.substring(0,oldFilePath.lastIndexOf(".")) + "_" + time + postfix ;
		this.filePath = filePath.replaceAll("\\\\", "/");
		this.fileName=filePath.substring(filePath.lastIndexOf("/")+1);
		
		try {
			input=new FileInputStream(oldFilePath);//Ìæ»»Ç°ÎÄ¼þµÄÎ»ÖÃ£¬¿ÉÌæ»»ÎªÈÎºÎÂ·¾¶ºÎºÍÎÄ¼þÃû
			output=new FileOutputStream(filePath);//Ìæ»»ºóÎÄ¼þµÄÎ»ÖÃ£¬¿ÉÌæ»»ÎªÈÎºÎÂ·¾¶ºÎºÍÎÄ¼þÃû
			int in=input.read();
			while(in!=-1){
				output.write(in);
				in=input.read();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			try {
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LogInfo.output("ÎÄ¼þÃû¸ü¸Ä³É¹¦");
		if(oldFile.exists()){
			oldFile.delete();
		}
	}

	/**
	 * ¡¾É¾³ýÎÄ¼þ¡¿:ÓÃ»§µã»÷É¾³ýÎÄ¼þ»òÖØÐÂÑ¡ÔñÊ±Ö´ÐÐ
	 * @param strFilePath
	 */
	public void deleteDoc(String strFilePath){
		File file=new File(strFilePath);
		if(file.exists() && file.isFile()){
			file.delete();
		}
	}
	
	/**
	 * ¡¾»ñÈ¡ÎÄ¼þÂ·¾¶¡¿
	 * @return
	 */
	public String getFilePath(){
		return filePath;
	}
	
	/**
	 * ¡¾»ñÈ¡ÎÄ¼þÃû¡¿
	 * @return
	 */
	public String getFileName(){
		return fileName;
	}
	
	/**
	 *¡¾»ñÈ¡ÎÄ¼þÒ³Êý ¡¿
	 * @return
	 */
	public int getPages(){
		LogInfo.output("×¼±¸»ñÈ¡ÎÄ¼þÒ³Êý...");
		//3.¸ù¾Ýºó×ºÃû»ñÈ¡ÎÄ¼þÒ³Êý
		try {
			switch (postfix) {
			case ".doc":
				WordExtractor docFile = new WordExtractor(new FileInputStream(filePath));
				pages = docFile.getSummaryInformation().getPageCount();// ×ÜÒ³Êý
				break;
			case ".docx":
				XWPFDocument docxFile = new XWPFDocument(POIXMLDocument.openPackage(filePath));
				pages = docxFile.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();// ×ÜÒ³Êý
				break;
			case ".ppt":
				InputStream isPPT=new FileInputStream(filePath);
				SlideShow pptFile = new SlideShow(isPPT);
				pages=pptFile.getSlides().length;
				break;
			case ".pptx":
				XSLFSlideShow xslfSlideShow=new XSLFSlideShow(filePath);
				XMLSlideShow pptxFile=new XMLSlideShow(xslfSlideShow);
				pages=pptxFile.getSlides().length;
				break;
				
			//txt  xls¾ù×ª»¯ÎªpdfºóÔÙÅÐ¶ÏÒ³Êý
			case ".txt":
			case ".xls":
			case ".xlsx":
				DocConverter converter=new DocConverter(filePath);
				converter.conver();
				PdfReader otherFile=new PdfReader(converter.getpdfPath());
				pages=otherFile.getNumberOfPages();
				break;
				
			case ".pdf":
				PdfReader pdfFile=new PdfReader(filePath);
				pages=pdfFile.getNumberOfPages();
				break;
			default:
				pages=0;
				break;
			}
			LogInfo.output("ÒÑ»ñÈ¡ÎÄ¼þÒ³Êý...");
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LogInfo.output("»ñÈ¡ÎÄ¼þÒ³Êý£ºÕÒ²»µ½ÎÄ¼þ");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pages;
	}
}
