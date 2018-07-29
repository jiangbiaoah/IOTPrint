<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="checkLogin.jsp" %>
<%String userName=(String)session.getAttribute("userName"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>IOTPrint支付方式</title>
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
</style>
<script type="text/javascript">
	function checkSupport(){
		alert("暂不支持该支付方式，请使用IOTPrint钱包付款！")
	}
</script>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
		<div class="container">
		<div class="navbar-header">
			<a href="#" class="navbar-brand"
				style="margin: 0; padding: 0;"><img src="./img/PayMethod.jpg"
				alt="LOGO" style="height:170%"></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		</div>
	</nav>
	<div style="width:100%;height:150px;"></div>
	
	<div style="width:800px;height:400px;margin:0 auto;">
		<div><label for="payWay" style="width:80%;height:50px;margin:0 auto;font-size:20px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;">请选择支付方式</label></div>
		<div style="width:800px;height:200px">
			<div style="width:400px;height:200px;float:left;"><div id="Alipay" style="float:left;margin:0 auto;"><a href="#" onclick="checkSupport();"><img src="./img/Alipay.jpg" alt="支付宝" style="width:90%"></a></div></div>
			<div style="width:400px;height:200px;float:left;"><div id="Alipay" style="float:left;"><a href="#" onclick="checkSupport();"><img src="./img/UnionPay.jpg" alt="银联支付" style="width:90%"></a></div></div>
		</div>
		<div style="width:800px;height:200px">
			<div style="width:400px;height:200px;float:left;"><div id="Alipay" style="float:left;margin:0 auto;"><a href="#" onclick="checkSupport();"><img src="./img/WeChat.jpg" alt="微信" style="width:90%"></a></div></div>
			<div style="width:400px;height:200px;float:left;"><div id="Alipay" style="float:left;"><a href="IOTPrintPay.action"><img src="./img/IOTPrintPay.jpg" alt="IOTPrint钱包" style="width:90%"></a></div></div>
		</div>
	</div>
</body>
</html>