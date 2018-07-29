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
import com.Atschool.Class.SQLOperate;
@WebServlet("/IOTPrintPayServlet")
public class IOTPrintPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IOTPrintPayServlet() {
        super();
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		//��ֹ�ڴ�ҳ��ˢ�º���ִ����һ�����´��룬����ͬһ�ļ��ٴ��ϴ�
		if(session.getAttribute("once")=="false"){
			session.setAttribute("once", "true");
			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/IOTPrintPay.jsp");
			dispatcher.forward(request, response);
		}else {
			session.setAttribute("once", "false");
		}
		
		String userName=(String)session.getAttribute("userName");
		double cost=(double)session.getAttribute("cost");
		String submitOrderTime=(String)session.getAttribute("submitOrderTime");
		
		boolean boolPay=false;
		boolPay=SQLOperate.pay(userName, cost);
		if(boolPay){
			session.setAttribute("payResult", "true");
			LogInfo.output("����ɹ���");
			if(!userName.equals("TestID")){
				SQLOperate.updateOrderState(userName, submitOrderTime, 1);
			}
		}else {
			LogInfo.output("����ʧ�ܡ�");
			session.setAttribute("payResult", "false");
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/IOTPrintPay.jsp");
		dispatcher.forward(request, response);
	}
}
