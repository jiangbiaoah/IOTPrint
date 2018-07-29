package com.Atschool.Servlet;

/**
 * 订单处理模块
 * 将用户的文件信息保存到数据库中
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
		LogInfo.output("准备提交订单...");
		//获取session中的用户文件信息,保存在userPrintInfos中
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		//防止在此页面刷新后又执行了一次以下代码，导致同一文件再次上传
//		if(session.getAttribute("once")=="false"){
//			session.setAttribute("once", "true");
//			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/pay.jsp");
//			dispatcher.forward(request, response);
//		}else {
//			session.setAttribute("once", "false");
//		}
		
		UserPrintInfoAll userPrintInfos=(UserPrintInfoAll)session.getAttribute("userPrintInfos");
		//创建订单类对象，将其保存到session中
		UserOrderInfo userOrderInfo=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmm");
		//String time=sdf.format(new Date());
		
		//获取页面传来的收货信息
		String userName=(String)session.getAttribute("userName");
		String consignee=request.getParameter("consignee");	//收件人
		String phoneNum=request.getParameter("phoneNum");	//收件人手机号
		String address=request.getParameter("address");		//收件人地址
		double cost=userPrintInfos.getCost();				//订单总费用
		int orderState=0;//0：未付款
		String submitOrderTime=sdf.format(new Date());
		
		//设置订单信息
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
		//将提交订单时间添加到用户文件信息表中
		for(int i=0;i<userPrintInfos.getFileNum();i++){
			userPrintInfos.getInfo(i).setSubmitOrderTime(submitOrderTime);
		}
		
		//将订单信息和用户文件信息保存到数据库
		boolean save1=true;
		boolean save2=true;
		//如果是测试用户，则不将信息保存到数据库
		if(!userName.equals("TestID")){
			save1=SQLOperate.saveUserOrderInfo(userOrderInfo);
			save2=SQLOperate.saveUserPrintInfo(userPrintInfos);
		}
		if(!save1 && !save2){
			//servlet跳转到WEB-INF下的页面
			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			String mailContent="  已收到您的订单："
					+"感谢您对IOTPrint物联打印团队的支持！";
			//邮件通知用户已经收到该订单
			MailSenderInfo mailSenderInfo=new MailSenderInfo();
			mailSenderInfo.setToAddress(phoneNum);
			mailSenderInfo.setSubject("IOTPrint物联打印通知");
			mailSenderInfo.setContent(mailContent);
			
			boolean boolSendMail=true;
			//boolSendMail = SendMail.sendTextMail(mailSenderInfo);
			if(boolSendMail){
				LogInfo.output("邮件发送成功。");
			}else {
				LogInfo.output("邮件发送失败！");
			}
			LogInfo.output("订单信息提交成功，即将转向付款页面...");
			//response.sendRedirect("./pay.jsp");
			RequestDispatcher dispatcher=request.getRequestDispatcher("./WEB-INF/jsp/pay.jsp");
			dispatcher.forward(request, response);
		}
	}
}
