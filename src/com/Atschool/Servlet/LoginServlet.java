package com.Atschool.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Atschool.Class.LogInfo;
import com.Atschool.Class.SQLOperate;
import com.Atschool.JavaBean.UserInfo;

/**
 * �û���¼Servlet��
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -3009431503363456775L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�û���������
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		// �����û������ѯ�û�
		UserInfo userInfo = SQLOperate.matchUserInfo(userName, password);
		// ��user=null�����û�������
		if (userInfo != null) {
			LogInfo.output("�û���¼�ɹ���������ת����ҳ...");
			// ���û��������session��
			HttpSession session = request.getSession();
			session.removeAttribute("userInfo");//�Ƴ�session��ֵ
			session.removeAttribute("userOrderInfo");
			session.removeAttribute("userPrintInfos");
			session.removeAttribute("payResult");
//			session.invalidate();//�����ǰsession�����������Ϣ���ᱨ��Session  has already been invalidated
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userName", userInfo.getUserName());
			
			//response.sendRedirect("./index.jsp");
			RequestDispatcher dispatcher=request.getRequestDispatcher("./index.jsp");
			dispatcher.forward(request, response);
		} else {
			// ��¼ʧ��
			LogInfo.output("�û���¼ʧ�ܣ�");
			PrintWriter out=response.getWriter();
			out.print("<script>alert('�û�����������������µ�¼��');window.location.href='./userLogin.jsp'</script>");
			//response.sendRedirect("./userLogin.jsp");
		}
	}

}
