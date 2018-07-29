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
 * �û�ע���Servlet��
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 5280356329609002908L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ������
		String PIN = request.getParameter("PIN");
		if(!PIN.equals("1369")){
			LogInfo.output("��������󣬲�����ע�ᣡ");
			response.getWriter().print("<script>alert('�������������ϵ����Ա��');window.location.href='./userRegistration.jsp'</script>");
		}else {
			LogInfo.output("��������ȷ��׼��ע���û�...");
			
			// ��ȡ�û���
			String userName = request.getParameter("userName").trim();
			// ��ȡ����
			String password = request.getParameter("password").trim();
			// ��ȡ��ϵ�绰
			String phoneNum = request.getParameter("phoneNum").trim();
			// ��ȡ��������
			String email = request.getParameter("email").trim();
			
			if (userName != null && !userName.isEmpty()) {
				if (SQLOperate.getUserInfo(userName)==null) {
					// ʵ����һ��User����
					UserInfo userInfo = new UserInfo();
					// ���û������е����Ը�ֵ
					userInfo.setUserName(userName);
					userInfo.setPassword(password);
					userInfo.setEmail(email);
					userInfo.setPhoneNum(phoneNum);
					// �����û�ע����Ϣ
					SQLOperate.saveUserInfo(userInfo);
					SQLOperate.charge(userName,20);
					
					//---------------�ʼ�֪ͨ�û�ע��ɹ�-------------------
					String mailContent="���ѳɹ�ע��IOTPrint�˺ţ�"+userName+"������ӭ����Ϊ���ǵ�һԱ��\n"
							+"����ʹ��������20Ԫ��ӡ�����½�˺ź��ҵ�Ǯ���в鿴��\n"
							+"                  ��л����IOTPrint������ӡ�Ŷӵ�֧�֣�";
					//�ʼ�֪ͨ�û��Ѿ��յ��ö���
					MailSenderInfo mailSenderInfo=new MailSenderInfo();
					mailSenderInfo.setToAddress(email);
					mailSenderInfo.setSubject("IOTPrint������ӡ֪ͨ");
					mailSenderInfo.setContent(mailContent);
					
					boolean boolSendMail=false;
					boolSendMail = SendMail.sendTextMail(mailSenderInfo);
					if(boolSendMail){
						LogInfo.output("�ʼ����ͳɹ���");
					}else {
						LogInfo.output("�ʼ�����ʧ�ܣ�");
					}
					//-----------------------------------------------------
					HttpSession session=request.getSession();
					session.setAttribute("userName", userName);
					
					LogInfo.output("�û�ע��ɹ���������ת����¼����...");
					response.getWriter().print("<script>alert('�û�ע��ɹ�����������¼����');window.location.href='./userLogin.jsp'</script>");
				} else {
					LogInfo.output("�û��Ѵ���...");
					PrintWriter out=response.getWriter();
					out.print("<script>alert('�û��Ѵ���,��������¼����');window.location.href='./userLogin.jsp'</script>");
				}
			}
			// ת����userLogin.jspҳ��
			//request.getRequestDispatcher("./userLogin.jsp").forward(request, response);
			//response.sendRedirect("./userLogin.jsp");
		}
	}
}
