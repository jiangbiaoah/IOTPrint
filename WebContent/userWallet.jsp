<%@page import="com.Atschool.Class.SQLOperate"%>
<%@page import="com.Atschool.Class.LogInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./WEB-INF/jsp/checkLogin.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String userName = (String) session.getAttribute("userName");
	double balance=0;
	
	if(userName!=null){
		LogInfo.output("准备从数据库中获取用户余额信息...");
		//SQL:从数据库中获取用户余额
		SQLOperate sqlo=new SQLOperate();
		balance=sqlo.getBalance(userName);
		LogInfo.output("已从数据库中获取用户余额信息");
	}
	if(userName==null){
		userName="未登录";
	}else{
		userName="欢迎您："+userName;
	}
%>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>IOTPrint钱包</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />
<link rel="stylesheet" href="./css/userWallet.css">
<style>
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
</style>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
	<div class="container">
		<div class="navbar-header">
			<a href="./index.jsp" class="navbar-brand"
				style="margin: 0; padding: 0;"><img src="./img/LR.png"
				alt="LOGO"></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div id="welcome1">
			<p id="welcome2">
				<strong><%=userName %>1111</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
		</div>
		<br />
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav navbar-right" style="margin-top:0;">
				<li><a href="./index.jsp"><span
						class="glyphicon glyphicon-home"></span> <font color="#92a8d1"><B>IOTPrint首页</B></font></a></li>
				<li><a href="./userPrint.jsp"><span
						class="glyphicon glyphicon-print"></span> <font color="#92a8d1"><B>开始打印</B></font></a></li>
				<li><a href="./userOrder.jsp"><span
						class="glyphicon glyphicon-list-alt"></span> <font color="#92a8d1"><B>我的订单</B></font></a></li>
				<li><a href="./userWallet.jsp"><span
						class="glyphicon glyphicon-tag"></span> <font color="#92a8d1"><B>我的钱包</B></font></a></li>
				<li><a href="./userInfo.jsp"><span
						class="glyphicon glyphicon-comment"></span> <font color="#92a8d1"><B>个人信息</B></font></a></li>
				<li><a href="./service.jsp"><span
						class="glyphicon glyphicon-globe"></span> <font color="#92a8d1"><B>关于我们</B></font></a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div id="divContent">

		<div class="main">
			<div class="h20"></div>
			<div class="myWallet">
				<div class="money">
					<p class="tit">
						<B>余额</B>
					</p>
					<p class="con">
						<B>￥&nbsp;<%=balance %></B>
					</p>
				</div>
				<br />
				<div class="btn">
					<a class="RechargeBtn">充值</a><a
						 class="seemore">查看明细</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 主要内容END -->
	<div class="footer" style="width: 80%; margin: 0 auto;">南京邮电大学自动打印平台
		请使用IE11、Chrome、Firefox、360浏览器访问</div>


	<script src="./js/jquery-2.2.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>
