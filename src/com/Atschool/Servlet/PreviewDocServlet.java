package com.Atschool.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Atschool.Class.DocConverter;
import com.Atschool.JavaBean.UserPrintInfoAll;

/**
 * Servlet implementation class ConvertToSwf
 */
@WebServlet("/ConvertToSwf")
public class PreviewDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PreviewDocServlet() {
        super();
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���Ϣsession��Χ
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		UserPrintInfoAll userPrintInfos=(UserPrintInfoAll)session.getAttribute("userPrintInfos");
		
		//��ȡԤ����ť��idֵ�����ж�Ԥ���Ǹ��ĵ�
		int i=Integer.parseInt(request.getParameter("id"));
		String filePath = userPrintInfos.getInfo(i).getFilePath();
		//System.out.println("��ť��id="+i);
		
		//������������Ԥ���ӿ�ʵ��Ԥ��
		String userName=(String)session.getAttribute("userName");
		String fileName=userPrintInfos.getInfo(i).getFileReamName();
		String sendFilePath="http://139.196.234.4:8080/dcsUserCenter/checkUrl.do?"
				+ "k=30750321"
				+ "&url=http://115.159.30.21/IOTPrintFolder/"
				+ userName+"/"
				+ fileName;
		//response.sendRedirect(sendFilePath);
		request.getRequestDispatcher(sendFilePath).forward(request, response);
		
		/*
		try {
			filePath=filePath.replace("\\\\", "/");
			//ִ���ļ�ת��
			DocConverter d = new DocConverter(filePath);
			d.conver();
			//����swf���·�����Ա㴫�ݸ�flexpaper������  
			//����·��Ϊ        D:/userPrintFolder/nickname/document/test.swf
			//���·��Ӧ��Ϊ    /userPrintFolder/nickname/document/test.swf
			//String swfFilePath = "/userPrintFolder/Tigerkin"+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
			String swfFilePath = d.getswfPath().substring(2);
			
			request.setAttribute("swfFilePath", swfFilePath);
			request.getRequestDispatcher("./WEB-INF/jsp/OnlinePreview.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("./WEB-INF/error.jsp").forward(request, response);
			e.printStackTrace();
		}*/
	}

}
