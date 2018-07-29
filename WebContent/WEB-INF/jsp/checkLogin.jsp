<%@page import="com.Atschool.JavaBean.UserInfo"%>
<%
	UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	if(userInfo==null){
		response.sendRedirect("./userLogin.jsp");
	}
%>