package com.Atschool.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Atschool.Class.LogInfo;
import com.Atschool.JavaBean.UserPrintInfoAll;

@WebServlet("/DeleteDoc")
public class DeleteDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDocServlet() {
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
		LogInfo.output("׼��ɾ���û��ĵ�...");
		// ��ȡ�û���Ϣsession��Χ
		HttpSession session = request.getSession();
		UserPrintInfoAll userPrintInfos = (UserPrintInfoAll) session.getAttribute("userPrintInfos");

		// ��ȡԤ����ť��idֵ�����ж�Ԥ���Ǹ��ĵ�
		int i = Integer.parseInt(request.getParameter("id"));
		userPrintInfos.delSelectedDoc(i);
		
		LogInfo.output("�û��ĵ���ɾ��");
		//response.sendRedirect("./userUpload.jsp");
		RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/userUpload.jsp");
		dispatcher.forward(request, response);
	}

}
