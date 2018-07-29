package com.Atschool.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Atschool.Class.LogInfo;
import com.Atschool.JavaBean.MailSenderInfo;
@WebServlet("/GetPINByEmail")
public class GetPINByEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetPINByEmail() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String mailContent="  已收到您的订单："
				+"感谢您对IOTPrint物联打印团队的支持！";
		//邮件通知用户已经收到该订单
		MailSenderInfo mailSenderInfo=new MailSenderInfo();
		//mailSenderInfo.setToAddress(phoneNum);
		mailSenderInfo.setSubject("IOTPrint物联打印通知");
		mailSenderInfo.setContent(mailContent);
		
		boolean boolSendMail=false;
		//boolSendMail = SendMail.sendTextMail(mailSenderInfo);
		if(boolSendMail){
			LogInfo.output("邮件发送成功。");
		}else {
			LogInfo.output("邮件发送失败！");
		}
	}

}
