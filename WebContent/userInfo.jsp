<%@page import="com.Atschool.Class.LogInfo"%>
<%@page import="com.Atschool.Class.SQLOperate"%>
<%@page import="com.Atschool.JavaBean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./WEB-INF/jsp/checkLogin.jsp"%>
<%
	String userName = (String) session.getAttribute("userName");
	
	String email=null;
	String phoneNum=null;
	String age=null;
	String birthday=null;
	String constellation=null;
	String address=null;
	
	if(userName!=null){
		LogInfo.output("准备从数据库中读取用户信息到页面中...");
		//从数据库中获取用户信息资料
		UserInfo userInfo1=SQLOperate.getUserInfo(userName);
		email=userInfo1.getEmail();
		phoneNum=userInfo1.getPhoneNum();
		age=String.valueOf(userInfo1.getAge());
		birthday=userInfo1.getBirthday();
		constellation=userInfo1.getConstellation();
		address=userInfo1.getAddress();
		
		if(email==null || email.trim()==""){
			email="未填写";
		}
		if(phoneNum==null || phoneNum.trim()==""){
			phoneNum="未填写";
		}
		if(age==null || age.trim()==""){
			age="未填写";
		}
		if(birthday==null || birthday.trim()==""){
			birthday="未填写";
		}
		if(constellation==null || constellation.trim()==""){
			constellation="未填写";
		}
		if(address==null || address.trim()==""){
			address="未填写";
		}
		LogInfo.output("已从数据库中读取用户信息到页面中");
	}
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11">
<title>IOTPrint个人信息</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />
<link rel="stylesheet" href="./css/userOrder.css">
<script type="text/javascript"
	src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
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

#interval00 {
	height: 61px;
	background-color: #F0F0F0;
}

html, body, * {
	background-color: #FFF;
}

#container {
	width: 1000px;
	height: 80%;
	background-color: white;
	font-family: 'Source Sans Pro', 'Helvetica Neue', 'Helvetica', 'Arial',
		'sans-serif';
	font: 15px;
}

#userInfo {
	margin: 0 auto;
}

ul {
	list-style-type: none;
	padding-top: 10px;
}

ul li {
	padding-top: 10px;
}

ul li input {
	width: 250px;
	height: 40px;
	text-align: center;
	vertical-align: center;
}

ul li input:hover {
	background-color: rgb(252, 193, 109);
	border-radius: 45%;
}

#useInfoBtn1 {
	margin: 0 auto;
	width: 160px;
	height: 50px;
}

#useInfoBtn10 {
	width: 90px;
	height: 40px;
	margin-left: 70px;
	font-size: 20px;
	text-align: center;
	vertical-align: center;
	padding-bottom: 6px;
}

#useInfoBtn10:hover {
	background-color: blue;
	border-radius: 20px;
	color: yellow;
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
				<strong>欢迎您：<%=userName %></strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

	<div id="interval00"></div>
	<div id=container>
		<div id="userInfo" style="width: 100%;">
			<div style="height: 7%; width: 99%; align: center;"></div>
			<h1 style="padding-top: 3%; text-align: center">个人信息</h1>
			<div style="height: 4%; width: 99%; align: center;"></div>
			<div style="margin-left: 30%; margin-right: 20%">
				<table class="table">
					<!-- <caption>基本的表格布局</caption> -->
					<thead>
						<tr>
							<th style="text-align: right; width: 80px;">昵&nbsp&nbsp&nbsp称</th>
							<td style="width: 10%"></td>
							<td><%=userName %></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="text-align: right; width: 80px;">邮&nbsp&nbsp&nbsp箱</th>
							<td style="width: 10%"></td>
							<td><%=email %></td>
						</tr>
						<tr>
							<th style="text-align: right; width: 80px;">手&nbsp&nbsp&nbsp机</th>
							<td style="width: 10%"></td>
							<td><%=phoneNum %></td>
						</tr>
						<tr>
							<th style="text-align: right; width: 80px;">年&nbsp&nbsp&nbsp龄</th>
							<td style="width: 10%"></td>
							<td><%=age %></td>
						</tr>
						<tr>
							<th style="text-align: right; width: 80px;">生&nbsp&nbsp&nbsp日</th>
							<td style="width: 10%"></td>
							<td><%=birthday %></td>
						</tr>
						<tr>
							<th style="text-align: right; width: 80px;">星&nbsp&nbsp&nbsp座</th>
							<td style="width: 10%"></td>
							<td><%=constellation %></td>
						</tr>
						<tr>
							<th style="text-align: right; width: 80px;">所在地</th>
							<td style="width: 10%"></td>
							<td><%=address %></td>
						</tr>
					</tbody>
				</table>
				<br />
				<div id="useInfoBtn1">
					<a href="editUserInfo.action"><button type="button"
							id="useInfoBtn10" class="btn btn-default" width="50px">修改</button></a>
				</div>
			</div>
		</div>

		<script src="./js/jquery-2.2.1.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
</body>
</html>
