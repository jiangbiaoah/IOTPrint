package com.Atschool.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Atschool.Class.LogInfo;
import com.Atschool.Class.SQLOperate;
import com.Atschool.JavaBean.UserInfo;

@WebServlet("/EditUserInfo")
public class EditUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EditUserInfoServlet() {
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
		String userName=(String)session.getAttribute("userName");
		
		//�ӱ��л�ȡ�û���Ϣ
		//String email=request.getParameter("email");
		String phoneNum=request.getParameter("phoneNum");
		//int age=Integer.parseInt(request.getParameter("age"));
		int age=0;
		String birthday=request.getParameter("birthday");
		String constellation=request.getParameter("constellation");
		String address=request.getParameter("address");
		
		//���û���Ϣ��ӵ�userInfo��
		UserInfo userInfo=new UserInfo();
		userInfo.setUserName(userName);
		//userInfo.setEmail(email);
		userInfo.setPhoneNum(phoneNum);
		userInfo.setAge(age);
		userInfo.setBirthday(birthday);
		userInfo.setConstellation(constellation);
		userInfo.setAddress(address);
		
		boolean boolUpdateUserInfo=SQLOperate.updateUserInfo(userInfo);
		PrintWriter out=response.getWriter();
		if(boolUpdateUserInfo==true){
			LogInfo.output("�û���Ϣ���ĳɹ���");
			out.print("<script>alert('��Ϣ���ĳɹ���');window.location.href='./userInfo.jsp'</script>");
		}else {
			LogInfo.output("�û���Ϣ����ʧ�ܣ�");
			out.print("<script>alert('ϵͳ�������Ժ����ԣ�');window.location.href='./userInfo.jsp'</script>");
		}
	}

}
