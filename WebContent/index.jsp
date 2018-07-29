<%@page import="com.Atschool.JavaBean.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!public String codeToString(String str) {//处理中文字符串的函数
		String s = str;
		try {
			byte tempB[] = s.getBytes("ISO-8859-1");
			s = new String(tempB);
			return s;
		} catch (Exception e) {
			return s;
		}
	}%>
<%
	String userName = (String) session.getAttribute("userName");
	if(userName==null){
		userName="点击登录";
	}else{
		userName="欢迎您："+userName;
	}
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>IOTPrint物联打印平台</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />
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
				<a href="./userLogin.jsp"><strong><%=userName %></strong></a>
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

	<div id="myCarousel" class="carousel slide">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="item active" style="background: #F5E4DC;">
				<a href="./index.jsp"><img src="./img/2.jpg" alt="第一张"></a>
			</div>
			<div class="item" style="background: #F5E4DC;">
				<a href="./userPrint.jsp"><img src="./img/1.gif" alt="第二张"></a>
			</div>
			<div class="item" style="background: #0168b7;">
				<a href="./userPrint.jsp"><img src="./img/3.jpg" alt="第三张"></a>
			</div>
			<a href="#myCarousel" data-slide="prev" class="carousel-control left">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a> <a href="#myCarousel" data-slide="next"
				class="carousel-control right"> <span
				class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
	</div>
	<div class="tab1">
		<div class="container">
			<h2 class="tab-h2">「 为什么选择IOTPrint打印平台 」</h2>
			<p class="tab-p">自动化打印流程，大批量订单的快速处理，多打印任务的优化分配！</p>
			<div class="row">
				<div class="col-md-6 col">
					<div class="media">
						<div class="media-left media-top">
							<a href="#"> <img class="media-object" src="./img/yewu1.jpg"
								alt="...">
							</a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">业务范围</h4>
							<p>论文、书本等打印，并进行装订</p>
							<p>照片、图片的快速洗印，支持送件上门</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col">
					<div class="media">
						<div class="media-left media-top">
							<a href="#"> <img class="media-object"
								src="./img/youshi1.jpg" alt="...">
							</a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">云打印优势</h4>
							<p>省去U盘携带资料，操作更加快速！</p>
							<p>便捷网上平台支付，摆脱找零烦恼！</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col">
					<div class="media">
						<div class="media-left media-top">
							<a href="#"> <img class="media-object"
								src="./img/liucheng1.jpg" alt="...">
							</a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">学生打印步骤</h4>
							<p>1：整理好待打印文件。</p>
							<p>2：点击"开始打印"按钮，并进入登录界面。</p>
							<p>3：如果没有账号，请先点击"账号注册"，然后登录。</p>
							<p>4：上传文件，并选择支付，完成订单即可！</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col">
					<div class="media">
						<div class="media-left media-top">
							<a href="#"> <img class="media-object" src="./img/hezuo1.png"
								alt="...">
							</a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">打印店合作事宜</h4>
							<p>联系电话：15722928812</p>
							<p>email：IOTPrint@163.com</p>
							<p>地址：江苏省南京市南京邮电大学物联网学院</p>
							<p>我们会在收到贵方消息后，即时处理，多谢合作！</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer id="footer" class="text-muted">
		<div class="container">
			<p>公司咨询 | 合作事宜 | 版权投诉</p>
			<p>IOTPrint云印 2016-2017. Powered by IOTPrint.</p>
		</div>
		</footer>

		<script src="./js/jquery-2.2.1.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$('#myCarousel').carousel({ //设置自动播放/2 秒 
				interval : 2000,
			});
		</script>
</body>
</html>
