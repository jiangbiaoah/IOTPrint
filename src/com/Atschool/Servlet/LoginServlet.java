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
 * 用户登录Servlet类
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -3009431503363456775L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取用户名和密码
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		// 根据用户密码查询用户
		UserInfo userInfo = SQLOperate.matchUserInfo(userName, password);
		// 若user=null，则用户不存在
		if (userInfo != null) {
			LogInfo.output("用户登录成功，即将跳转至主页...");
			// 将用户对象放入session中
			HttpSession session = request.getSession();
			session.removeAttribute("userInfo");//移除session的值
			session.removeAttribute("userOrderInfo");
			session.removeAttribute("userPrintInfos");
			session.removeAttribute("payResult");
//			session.invalidate();//清除当前session的所有相关信息，会报错：Session  has already been invalidated
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userName", userInfo.getUserName());
			
			//response.sendRedirect("./index.jsp");
			RequestDispatcher dispatcher=request.getRequestDispatcher("./index.jsp");
			dispatcher.forward(request, response);
		} else {
			// 登录失败
			LogInfo.output("用户登录失败！");
			PrintWriter out=response.getWriter();
			out.print("<script>alert('用户名或密码错误，请重新登录！');window.location.href='./userLogin.jsp'</script>");
			//response.sendRedirect("./userLogin.jsp");
		}
	}

}
