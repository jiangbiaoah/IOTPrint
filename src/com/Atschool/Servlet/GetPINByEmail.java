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
		
		
		String mailContent="  ���յ����Ķ�����"
				+"��л����IOTPrint������ӡ�Ŷӵ�֧�֣�";
		//�ʼ�֪ͨ�û��Ѿ��յ��ö���
		MailSenderInfo mailSenderInfo=new MailSenderInfo();
		//mailSenderInfo.setToAddress(phoneNum);
		mailSenderInfo.setSubject("IOTPrint������ӡ֪ͨ");
		mailSenderInfo.setContent(mailContent);
		
		boolean boolSendMail=false;
		//boolSendMail = SendMail.sendTextMail(mailSenderInfo);
		if(boolSendMail){
			LogInfo.output("�ʼ����ͳɹ���");
		}else {
			LogInfo.output("�ʼ�����ʧ�ܣ�");
		}
	}

}
