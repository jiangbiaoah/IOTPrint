package com.Atschool.Servlet;

/**
 * ��������ģ��
 * ���û����ļ���Ϣ���浽���ݿ���
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Atschool.Class.LogInfo;
import com.Atschool.Class.SQLOperate;
import com.Atschool.JavaBean.MailSenderInfo;
import com.Atschool.JavaBean.UserOrderInfo;
import com.Atschool.JavaBean.UserPrintInfoAll;

@WebServlet("/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SubmitOrderServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogInfo.output("׼���ύ����...");
		//��ȡsession�е��û��ļ���Ϣ,������userPrintInfos��
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		//��ֹ�ڴ�ҳ��ˢ�º���ִ����һ�����´��룬����ͬһ�ļ��ٴ��ϴ�
//		if(session.getAttribute("once")=="false"){
//			session.setAttribute("once", "true");
//			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/pay.jsp");
//			dispatcher.forward(request, response);
//		}else {
//			session.setAttribute("once", "false");
//		}
		
		UserPrintInfoAll userPrintInfos=(UserPrintInfoAll)session.getAttribute("userPrintInfos");
		//������������󣬽��䱣�浽session��
		UserOrderInfo userOrderInfo=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmm");
		//String time=sdf.format(new Date());
		
		//��ȡҳ�洫�����ջ���Ϣ
		String userName=(String)session.getAttribute("userName");
		String consignee=request.getParameter("consignee");	//�ռ���
		String phoneNum=request.getParameter("phoneNum");	//�ռ����ֻ���
		String address=request.getParameter("address");		//�ռ��˵�ַ
		double cost=userPrintInfos.getCost();				//�����ܷ���
		int orderState=0;//0��δ����
		String submitOrderTime=sdf.format(new Date());
		
		//���ö�����Ϣ
		userOrderInfo=new UserOrderInfo();
		userOrderInfo.setUserName(userName);
		userOrderInfo.setConsignee(consignee);
		userOrderInfo.setPhoneNum(phoneNum);
		userOrderInfo.setAddress(address);
		userOrderInfo.setCost(cost);
		userOrderInfo.setOrderState(orderState);
		userOrderInfo.setSubmitOrderTime(submitOrderTime);
		session.setAttribute("cost", cost);
		session.setAttribute("submitOrderTime", submitOrderTime);
		session.setAttribute("userOrderInfo", userOrderInfo);
		//���ύ����ʱ����ӵ��û��ļ���Ϣ����
		for(int i=0;i<userPrintInfos.getFileNum();i++){
			userPrintInfos.getInfo(i).setSubmitOrderTime(submitOrderTime);
		}
		
		//��������Ϣ���û��ļ���Ϣ���浽���ݿ�
		boolean save1=true;
		boolean save2=true;
		//����ǲ����û����򲻽���Ϣ���浽���ݿ�
		if(!userName.equals("TestID")){
			save1=SQLOperate.saveUserOrderInfo(userOrderInfo);
			save2=SQLOperate.saveUserPrintInfo(userPrintInfos);
		}
		if(!save1 && !save2){
			//servlet��ת��WEB-INF�µ�ҳ��
			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			String mailContent="  ���յ����Ķ�����"
					+"��л����IOTPrint������ӡ�Ŷӵ�֧�֣�";
			//�ʼ�֪ͨ�û��Ѿ��յ��ö���
			MailSenderInfo mailSenderInfo=new MailSenderInfo();
			mailSenderInfo.setToAddress(phoneNum);
			mailSenderInfo.setSubject("IOTPrint������ӡ֪ͨ");
			mailSenderInfo.setContent(mailContent);
			
			boolean boolSendMail=true;
			//boolSendMail = SendMail.sendTextMail(mailSenderInfo);
			if(boolSendMail){
				LogInfo.output("�ʼ����ͳɹ���");
			}else {
				LogInfo.output("�ʼ�����ʧ�ܣ�");
			}
			LogInfo.output("������Ϣ�ύ�ɹ�������ת�򸶿�ҳ��...");
			//response.sendRedirect("./pay.jsp");
			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/pay.jsp");
			dispatcher.forward(request, response);
		}
	}
}
