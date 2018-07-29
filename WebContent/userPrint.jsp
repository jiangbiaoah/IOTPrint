<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./WEB-INF/jsp/checkLogin.jsp"%>
<%
	String userName=(String)session.getAttribute("userName");
%>

<!DOCTYPE html>
<!-- saved from url=(0016)http://yindd.cn/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11">

<title>IOTPrint打印</title>

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />
<style>
body {
	margin: 0px auto;
	background-color: #F4F4F4;
}

#interval0 {
	height: 35px;
}

table[Attributes Style] {
	width: 100%;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	-webkit-margin-start: auto;
	-webkit-margin-end: auto;
	border-spacing: 0px;
}

user agent stylesheettable {
	display: table;
	border-collapse: separate;
	border-spacing: 2px;
	border-color: gray;
}

.home-icon1 {
	width: 350px;
	height: 350px;
	background-size: 100%;
	float: right;
	margin-left: 90px;
	/*text-align: center;*/
	line-height: 50px;
	font-size: 20px;
	color: #0498f8;
	margin-right: 20px;
}

.home-icon2 {
	width: 350px;
	height: 350px;
	float: left;
	margin-right: 90px;
	text-align: center;
	line-height: 50px;
	font-size: 20px;
	color: #0498f8;
}

.home-icon1 a {
	display: block;
	text-align: center;
	background: url(./img/txt1.jpg) no-repeat;
	background-size: 258px 258px;
	width: 260px;
	height: 260px;
}

.home-icon2 a {
	background: url(./img/img1.jpg) no-repeat;
	display: block;
	width: 260px;
	height: 260px;
}

.home-icon1 a:hover {
	background: url(./img/txt2.png) no-repeat;
}

.home-icon2 a:hover {
	background: url(./img/img2.png) no-repeat;
	display: block;
	background-size: 258px 258px;
}

#divContent {
	width: 800px;
	margin: 0 auto;
}

a {
	color: #666;
	text-decoration: none;
	outline: none;
	border: none;
}

.footer {
	margin: 0 auto;
	width: 800px;
}

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
<script type="text/javascript">
	function userUploadImg(){
		alert("该功能暂未开放,敬请期待......")
	}
</script>
</head>
<body style="cursor: default;">
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
		<div class="container">
			<div class="navbar-header">
				<a href="./index.jsp" class="navbar-brand"
					style="margin: 0; padding: 0;"><img src="./img/LR.png"
					alt="LOGO"></a>
			</div>
			<div id="welcome1">
				<p id="welcome2">
					<strong>欢迎您:<%=userName %></strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
	<div id="interval0"></div>
	<!--主要内容-->
	<div id="divContent">

		<div id="home-main">
			<div class="home">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tbody>
						<tr>
							<td height="150px" valign="middle">&nbsp;</td>
							<td height="150px" valign="middle">&nbsp;</td>
						</tr>
						<tr>
							<td width="50%" valign="middle"><div id="printMachine00"
									class="home-icon1">
									<a id="uploadImg1" href="userUpload.action">
										<div style="height:93%;width:100%;"></div>
										<div style="height:6.5%;width:100%;align:center;">文档打印</div>
									</a>
								</div></td>
							<td width="50%" valign="middle"><div id="printMachine01"
									class="home-icon2">
									<a id="uploadImg1" href="#" onclick="userUploadImg();">
										<div style="height:93%;width:100%;"></div>
										<div style="height:6.5%;width:100%;align:center;">照片打印</div>
									</a>
								</div></td>
						</tr>
						<tr>
							<td></td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<span id="provinceDIVSPAN"></span> <span id="cityDIVSPAN"></span> <span
			id="schoolDIVSPAN"></span>

	</div>
	<!-- 主要内容END -->
	<div class="footer">南京邮电大学自动打印小组 ©
		&amp;请使用IE11、Chrome、Firefox、360浏览器访问</div>
	<div class="theme-popover-mask"></div>
	<!--遮盖层-->

</body>
</html>