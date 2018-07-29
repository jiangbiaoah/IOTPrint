package com.Atschool.Servlet;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Atschool.Class.FileOperate;
import com.Atschool.Class.LogInfo;
import com.Atschool.JavaBean.UserPrintInfoAll;
import com.Atschool.JavaBean.Configuration;
import com.Atschool.JavaBean.UserPrintInfo;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@WebServlet("/UploadFile")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	@Override
	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public UploadFileServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//防止在此页面刷新后又执行了一次以下代码，导致同一文件再次上传
//		if(session.getAttribute("once")==null){
//			session.setAttribute("once", "true");
//			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/userUpload.jsp");
//			dispatcher.forward(request, response);
//		}else {
//			session.removeAttribute("once");
//		}
		
		LogInfo.output("准备上传文件...");
		UserPrintInfo userPrintInfo = new UserPrintInfo();
		String userName=(String)session.getAttribute("userName");
		String userFolder = Configuration.serverFolderPath + userName;
		File folder = new File(userFolder);
		String filePathServer=null;//用户文件在服务器上的路径
		
		//文件信息
		String fileName = null;
		int pages = 0;
		int copies = 1;
		double costEach = 0;
		String postfix = null; // 文件后缀

		// 判断用户文件夹是否存在，若不存在，则为用户创建个人文件夹
		if (!folder.exists()) {
			folder.mkdir();
		}

		int count = 0; // 记录用户上传的文件个数
		// 初始化对象
		SmartUpload su = new SmartUpload();
		su.initialize(config, request, response);
		// 设置文件最大容量
		// su.setMaxFileSize(10*1024*1024);
		try {
			// 设置上传文件类型txt,doc,docx,pptx,xls,pdf
			//su.setAllowedFilesList("txt");
			// 上传文件
			su.upload();
			count = su.save(userFolder);
			LogInfo.output("文件上传成功");
		} catch (SmartUploadException e) {
			LogInfo.output("文件上传失败");
			e.printStackTrace();
		}
		com.jspsmart.upload.File file = su.getFiles().getFile(0);
		filePathServer = userFolder + "/" + file.getFileName();
		
		//对文件进行重命名操作
		filePathServer = filePathServer.replaceAll("\\\\", "/");
		FileOperate fileOperate=new FileOperate(filePathServer);
		fileOperate.renameFile();
		filePathServer=fileOperate.getFilePath();//2 filePath
		fileName=fileOperate.getFileName();//3 fileName
		pages = fileOperate.getPages();//4 pages
		
		DecimalFormat df=new DecimalFormat(".00");
		costEach=0.1*pages*copies;	//6  costEach
		costEach=Double.parseDouble(df.format(costEach));
		
		
		// 将文件的信息保存到javabean中
		userPrintInfo.setUserName(userName);//1.userName
//		userPrintInfo.setFilePath(filePathServer); // 2.路径
		userPrintInfo.setFileName(fileName); // 3.文件名
		userPrintInfo.setPages(pages); // 4.文件页数
		userPrintInfo.setCopies(copies); // 5.文件份数
		userPrintInfo.setCostEach(costEach); // 6.每个文档的打印费用

		
		UserPrintInfoAll userPrintInfos = (UserPrintInfoAll)session.getAttribute("userPrintInfos");
		userPrintInfos.addInfo(userPrintInfo);

		//response.sendRedirect("./WEB-INF/jsp/userUpload.jsp");
		//servlet跳转到WEB-INF下的页面
		RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/userUpload.jsp");
		dispatcher.forward(request, response);
	}

}
