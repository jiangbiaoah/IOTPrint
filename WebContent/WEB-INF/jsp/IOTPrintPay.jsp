<%@page import="com.Atschool.Class.SQLOperate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.Atschool.JavaBean.UserPrintInfoAll"%>
<%@ include file="checkLogin.jsp"%>
<%
	session.setAttribute("once", "true");
	String userName=(String)session.getAttribute("userName");
	String payResult=(String)session.getAttribute("payResult");
	double cost=(double)session.getAttribute("cost");//本次打印的费用
	double balance=0;		//用户钱包余额
	String payButtonValue="";	//付款按钮文字
	String labelPrimpt="";	//付款按钮前的提示语："" 或 "付款完成"
	
	if(payResult==null){
		payResult="IOTPrintPayServlet.do";
		labelPrimpt="需支付："+cost+"元";
		payButtonValue="确认付款";
	}else if(payResult=="true"){
		payResult="userOrderInfo.action";
		labelPrimpt="付款完成";
		payButtonValue="确认";
	}else if(payResult.equals("false")){
		//LogInfo.output("付款失败");
	}
	if(userName!=null){
		balance=SQLOperate.getBalance(userName);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>在线支付</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />

</head>
<style>
	body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,form,fieldset,table,td,img,div,dl,dt,dd,input{margin:0;padding:0;}
	body{font-size:12px;}
	img{border:none;}
	li{list-style:none;}
	input,select,textarea{outline:none;}
	textarea{resize:none;}
	a{ text-decoration:none;}

	#welcome1 {
		height: 20px;
		width: 200px;
		float: right;
		color: blue;
		text-align: center; <!--
		color: #92A8D1; -->
		font-size: 14px;
		font-family: Source Sans Pro, Helvetica Neue, Helvetica, Arial,
			sans-serif;
	}

	#welcome2 {
		text-align: center;
		padding-top: 20px;
	}


	#Alipay{
		 width:350px;height:150px;
	}
	#Alipay:hover{
		width:400px;height:180px;
	}
	.contentMain{
		font-size: 20px;

	}
</style>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
		<div class="container">
		<div class="navbar-header">
			<a href="#" class="navbar-brand"
				style="margin: 0; padding: 0;"><img src="./img/IOTPrintPay.jpg"
				alt="LOGO" style="height:175%;overflow:hidden;"></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		</div>
	</nav>
	<div style="height:100px;margin: 0 auto;width:100%;"></div>
	<div class="contentMain" style="width:500px;height:80%;overflow:hidden;margin:100px auto;">
		<form action="<%=payResult %>" method="post">
			<div style="height:50px;width:100%;letter-spacing:4px;">
				<label>当前余额为：<%=balance %></label>
			</div>

			<div style="height:50px;width:100%;letter-spacing:3px;">
				<label><%=labelPrimpt %></label>
			</div>
			<div style="width:100%;height:40px;"></div>
			<div style="height:50px;width:100%;">
				<button type="submit" style="background-color:red;color:white;float:right;margin-right:100px;"><%=payButtonValue %></button>
			</div>
		</form>
	</div>
</body>
</html>