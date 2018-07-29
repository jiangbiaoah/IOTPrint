package com.Atschool.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Atschool.Class.LogInfo;
import com.Atschool.Class.SQLOperate;
import com.Atschool.JavaBean.UserInfo;
@WebServlet("/LoginTestServlet")
public class LoginTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginTestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName="TestID";
		UserInfo userInfo=SQLOperate.getUserInfo(userName);
		
		LogInfo.output("�����˺ŵ�¼�ɹ���������ת����ҳ...");
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", userInfo);
		session.setAttribute("userName", userInfo.getUserName());
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("./index.jsp");
		dispatcher.forward(request, response);
		
	}

}
