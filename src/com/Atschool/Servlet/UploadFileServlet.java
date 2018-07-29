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
		//��ֹ�ڴ�ҳ��ˢ�º���ִ����һ�����´��룬����ͬһ�ļ��ٴ��ϴ�
//		if(session.getAttribute("once")==null){
//			session.setAttribute("once", "true");
//			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/userUpload.jsp");
//			dispatcher.forward(request, response);
//		}else {
//			session.removeAttribute("once");
//		}
		
		LogInfo.output("׼���ϴ��ļ�...");
		UserPrintInfo userPrintInfo = new UserPrintInfo();
		String userName=(String)session.getAttribute("userName");
		String userFolder = Configuration.serverFolderPath + userName;
		File folder = new File(userFolder);
		String filePathServer=null;//�û��ļ��ڷ������ϵ�·��
		
		//�ļ���Ϣ
		String fileName = null;
		int pages = 0;
		int copies = 1;
		double costEach = 0;
		String postfix = null; // �ļ���׺

		// �ж��û��ļ����Ƿ���ڣ��������ڣ���Ϊ�û����������ļ���
		if (!folder.exists()) {
			folder.mkdir();
		}

		int count = 0; // ��¼�û��ϴ����ļ�����
		// ��ʼ������
		SmartUpload su = new SmartUpload();
		su.initialize(config, request, response);
		// �����ļ��������
		// su.setMaxFileSize(10*1024*1024);
		try {
			// �����ϴ��ļ�����txt,doc,docx,pptx,xls,pdf
			//su.setAllowedFilesList("txt");
			// �ϴ��ļ�
			su.upload();
			count = su.save(userFolder);
			LogInfo.output("�ļ��ϴ��ɹ�");
		} catch (SmartUploadException e) {
			LogInfo.output("�ļ��ϴ�ʧ��");
			e.printStackTrace();
		}
		com.jspsmart.upload.File file = su.getFiles().getFile(0);
		filePathServer = userFolder + "/" + file.getFileName();
		
		//���ļ���������������
		filePathServer = filePathServer.replaceAll("\\\\", "/");
		FileOperate fileOperate=new FileOperate(filePathServer);
		fileOperate.renameFile();
		filePathServer=fileOperate.getFilePath();//2 filePath
		fileName=fileOperate.getFileName();//3 fileName
		pages = fileOperate.getPages();//4 pages
		
		DecimalFormat df=new DecimalFormat(".00");
		costEach=0.1*pages*copies;	//6  costEach
		costEach=Double.parseDouble(df.format(costEach));
		
		
		// ���ļ�����Ϣ���浽javabean��
		userPrintInfo.setUserName(userName);//1.userName
//		userPrintInfo.setFilePath(filePathServer); // 2.·��
		userPrintInfo.setFileName(fileName); // 3.�ļ���
		userPrintInfo.setPages(pages); // 4.�ļ�ҳ��
		userPrintInfo.setCopies(copies); // 5.�ļ�����
		userPrintInfo.setCostEach(costEach); // 6.ÿ���ĵ��Ĵ�ӡ����

		
		UserPrintInfoAll userPrintInfos = (UserPrintInfoAll)session.getAttribute("userPrintInfos");
		userPrintInfos.addInfo(userPrintInfo);

		//response.sendRedirect("./WEB-INF/jsp/userUpload.jsp");
		//servlet��ת��WEB-INF�µ�ҳ��
		RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/userUpload.jsp");
		dispatcher.forward(request, response);
	}

}
