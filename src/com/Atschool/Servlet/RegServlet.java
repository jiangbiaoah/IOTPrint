package com.Atschool.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Atschool.Class.LogInfo;
import com.Atschool.Class.SQLOperate;
import com.Atschool.Class.SendMail;
import com.Atschool.JavaBean.MailSenderInfo;
import com.Atschool.JavaBean.UserInfo;

/**
 * 用户注册的Servlet类
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 5280356329609002908L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获取邀请码
		String PIN = request.getParameter("PIN");
		if(!PIN.equals("1369")){
			LogInfo.output("邀请码错误，不允许注册！");
			response.getWriter().print("<script>alert('邀请码错误，请联系管理员！');window.location.href='./userRegistration.jsp'</script>");
		}else {
			LogInfo.output("邀请码正确，准备注册用户...");
			
			// 获取用户名
			String userName = request.getParameter("userName").trim();
			// 获取密码
			String password = request.getParameter("password").trim();
			// 获取联系电话
			String phoneNum = request.getParameter("phoneNum").trim();
			// 获取电子邮箱
			String email = request.getParameter("email").trim();
			
			if (userName != null && !userName.isEmpty()) {
				if (SQLOperate.getUserInfo(userName)==null) {
					// 实例化一个User对象
					UserInfo userInfo = new UserInfo();
					// 对用户对象中的属性赋值
					userInfo.setUserName(userName);
					userInfo.setPassword(password);
					userInfo.setEmail(email);
					userInfo.setPhoneNum(phoneNum);
					// 保存用户注册信息
					SQLOperate.saveUserInfo(userInfo);
					SQLOperate.charge(userName,20);
					
					//---------------邮件通知用户注册成功-------------------
					String mailContent="您已成功注册IOTPrint账号（"+userName+"），欢迎您成为我们的一员。\n"
							+"初次使用已送您20元打印卷，请登陆账号后到我的钱包中查看。\n"
							+"                  感谢您对IOTPrint物联打印团队的支持！";
					//邮件通知用户已经收到该订单
					MailSenderInfo mailSenderInfo=new MailSenderInfo();
					mailSenderInfo.setToAddress(email);
					mailSenderInfo.setSubject("IOTPrint物联打印通知");
					mailSenderInfo.setContent(mailContent);
					
					boolean boolSendMail=false;
					boolSendMail = SendMail.sendTextMail(mailSenderInfo);
					if(boolSendMail){
						LogInfo.output("邮件发送成功。");
					}else {
						LogInfo.output("邮件发送失败！");
					}
					//-----------------------------------------------------
					HttpSession session=request.getSession();
					session.setAttribute("userName", userName);
					
					LogInfo.output("用户注册成功，即将跳转至登录界面...");
					response.getWriter().print("<script>alert('用户注册成功，点击进入登录界面');window.location.href='./userLogin.jsp'</script>");
				} else {
					LogInfo.output("用户已存在...");
					PrintWriter out=response.getWriter();
					out.print("<script>alert('用户已存在,点击进入登录界面');window.location.href='./userLogin.jsp'</script>");
				}
			}
			// 转发到userLogin.jsp页面
			//request.getRequestDispatcher("./userLogin.jsp").forward(request, response);
			//response.sendRedirect("./userLogin.jsp");
		}
	}
}
