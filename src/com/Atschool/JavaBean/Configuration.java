package com.Atschool.JavaBean;

public class Configuration {
	//------------------【本地参数】-----------------------------
	//SQL数据库
	public static final String sqlUrl = "jdbc:mysql://localhost:3306/IOTPrint";
	public static final String sqlUserName="root";	//数据库连接用户名
	public static final String sqlPassword="";		//数据库连接密码
	//文件
	public static final String serverFolderPath = "D://IOTPrintFolder//";//服务器端存储用户文件的文件夹
	public static final String converterTempPath = "D:/IOTPrintFolder/temp";//转码后的文档暂存位置
	public static final String pdf2swfexePath = "F:/install/onlinePreview/pdf2swf.exe";//本地pdf2swf.exe的位置


	//------------------【服务器参数】----------------------------
	//SQL数据库
//	public static final String sqlUrl = "jdbc:mysql://localhost:3306/IOTPrint";
//	public static final String sqlUserName="root";	//数据库连接用户名
//	public static final String sqlPassword="139169";		//数据库连接密码
//	//文件
//	public static final String serverFolderPath = "C://IOTPrintFolder//";//服务器端存储用户文件的文件夹
//	public static final String converterTempPath = "C:/IOTPrintFolder/temp";//转码后的文档暂存位置
//	public static final String pdf2swfexePath = "C:/Program Files (x86)/SWFTools/pdf2swf.exe";//云端服务器pdf2swf.exe的位置
}
