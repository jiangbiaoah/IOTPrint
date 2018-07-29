<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String userName = (String) session.getAttribute("userName");
	if(userName==null){
		userName="未登录";
	}else{
		userName="欢迎您："+userName;
	}
%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>IOTPrint服务</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />

</head>
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

#blank1 {
	height: 90px;
	maigin: 0px auto;
}
</style>
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
				<strong><%=userName %></strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
	<a name="p1"></a>
	<div id="blank1"></div>

	<div class="row" id="content">
		<div class="col-md-3 hidden-sm hidden-xs">
			<div class="list-group">
				<a class="list-group-item" href="#p1">1.机构简介</a> <a
					class="list-group-item" href="#p2">2.加入我们</a> <a
					class="list-group-item" href="#p3">3.联系方式</a>
			</div>
		</div>
		<div class="col-md-9 about">
			<!--     原先是name="1" -->
			<!-- <div id="blank1"></div> -->
			<h3>机构简介</h3>
			<a name="p2"></a>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IOTPrint物联智能打印公司是一家专门提供网络云打印平台的公司。由南京邮电大学校园发起的"万众创业，大众创新"的比赛而建立，始终坚持"诚信、合作、双赢"的企业理念，以提供"更高效的技术"和"更便捷的服务"为根本,努力成为未来云打印的领先企业。</p>


			<h3>加入我们</h3>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网络已深刻改变着人们的生活，本地化生活服务市场前景巨大，生活半径。团队坚信本地化生活服务与互联网的结合将会成就一家梦幻的公司，我们脚踏实地的相信梦想，我们相信你的加入会让生活半径更可能成为那家梦幻公司！生活半径人有梦想，有魄力，强执行力，但是要实现这个伟大的梦想，需要更多的有创业精神的你一路前行。公司将提供有竞争力的薪酬、完善的福利（五险一金）、期权、广阔的上升空间。只要你有能力、有激情、有梦想，愿意付出，愿意与公司共同成长，请加入我们！</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请发送您的简历到：IOTPrint@163.com，我们会在第一时间联系您！</p>
			<a name="p3"></a>
			<h3>联系方式</h3>
			<p>地址：江苏省南京市栖霞区仙林大学城南京邮电大学</p>
			<p>邮编：210046</p>
			<p>电话：15722928812</p>
		</div>
	</div>
	<footer id="footer">
	<div class="container">
		<p>公司咨询 | 合作事宜 | 版权投诉</p>
		<p>苏 ICP 备 12345678. © 2015-2016 IOTPrint云印网. Powered by IOTPrint.</p>
	</div>
	</footer>
</body>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
</body>
</html>