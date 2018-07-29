<%@page import="com.Atschool.Class.LogInfo"%>
<%@page import="com.Atschool.Class.SQLOperate"%>
<%@page import="com.Atschool.JavaBean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./checkLogin.jsp"%>
<%
	String userName=(String)session.getAttribute("userName");
	String email=null;
	String phoneNum=null;
	String age=null;
	String birthday=null;
	String constellation=null;
	String address=null;

	if(userName!=null){
		LogInfo.output("准备从数据库中读取用户信息到页面中...");
		//从数据库中获取用户信息资料
		SQLOperate sqlo	= new SQLOperate();
		UserInfo userInfo1=sqlo.getUserInfo(userName);
		email=userInfo1.getEmail();
		phoneNum=userInfo1.getPhoneNum();
		age=String.valueOf(userInfo1.getAge());
		birthday=userInfo1.getBirthday();
		constellation=userInfo1.getConstellation();
		address=userInfo1.getAddress();
	}
	if(email==null){
		email="";
	}
	if(phoneNum==null){
		phoneNum="";
	}
	if(age==null){
		age="";
	}
	if(birthday==null){
		birthday="";
	}
	if(constellation==null){
		constellation="";
	}
	if(address==null){
		address="";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>IOTPrint个人信息修改</title>
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



</style>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
		<div class="container">
		<div class="navbar-header">
			<a href="./index.jsp" class="navbar-brand"
				style="margin: 0; padding: 0;"><img src="./img/LR.jpg"
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
			<ul class="nav navbar-nav navbar-right" style="margin-top: 0;">
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
				<li><a href="./userNotice.jsp"><span
						class="glyphicon glyphicon-envelope"></span> <font color="#92a8d1"><B>我的通知</B></font></a></li>
			</ul>
		</div>
		</div>
	</nav>
	<div style="width:100%;height:95px;"></div>
	<div id="contentMain00" style="width:700px;height:600px;margin:0 auto;font-size:18px;color:blue;font-family:'微软雅黑';">
		<form action="EditUserInfoServlet.do" method="post" role="form" >
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:20px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>用户名：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="userName" rows="1" style="height:45px;" readonly><%=userName %></textarea></div>
			</div>
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:20px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>邮箱：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="email" rows="1" style="height:45px;" readonly><%=email %></textarea></div>
			</div>
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:20px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>手机号：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="phoneNum" rows="1" style="height:45px;"><%=phoneNum %></textarea></div>
			</div>
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:10px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>年&nbsp龄：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="age" rows="1" style="height:45px;"><%=age %></textarea></div>
			</div>
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:20px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>生&nbsp日：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="birthday" rows="1" style="height:45px;"><%=birthday %></textarea></div>
			</div>
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:10px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>星&nbsp座：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="constellation" rows="1" style="height:45px;"><%=constellation %></textarea></div>
			</div>
			<div class="form-group" style="width:90%;margin:0 auto;margin-top:10px;height:47px;">
				<div style="width:15%;margin-left:10%;margin-top:18px;float:left;"><p>所在地：</p></div>
				<div style="width:73%;margin-top:10px;float:left;"><textarea class="form-control" name="address" rows="1" style="height:45px;"><%=address %></textarea></div>
			</div>
			<div style="width:90%;height:30px; margin:0 auto;margin-top:30px;">
				<div style="width:200px;float:left;"></div>
				<div style="float:right;width:45%;margin-top:20px;"><button type="submit" class="btn btn-default">提交</button></div>
			</div>
		</form>
	</div>
	<footer id="footer">
		<div class="container">
			<p>团队咨询 | 合作事宜 | 版权投诉</p>
			<p>IOTPrint云印. Powered by IOTPrint.</p>
		</div>
	</footer>
</body>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
</body>
</html>