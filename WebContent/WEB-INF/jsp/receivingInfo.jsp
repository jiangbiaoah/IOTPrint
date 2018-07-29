<%@page import="javax.print.attribute.standard.Copies"%>
<%@page import="com.Atschool.JavaBean.UserInfo"%>
<%@page import="com.Atschool.JavaBean.UserOrderInfo"%>
<%@page import="com.Atschool.JavaBean.UserPrintInfo"%>
<%@page import="com.Atschool.JavaBean.UserPrintInfoAll"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.Atschool.Class.LogInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Atschool.Class.SQLOperate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="checkLogin.jsp"%>
<%
	session.setAttribute("once", "true");
	String userName=(String)session.getAttribute("userName");
	
	UserInfo userInfo2=SQLOperate.getUserInfo(userName);
	UserPrintInfoAll userPrintInfos=(UserPrintInfoAll)session.getAttribute("userPrintInfos");
	String phoneNum=userInfo2.getPhoneNum();
	String address=userInfo2.getAddress();
	int fileNum=userPrintInfos.getFileNum();
	double cost=userPrintInfos.getCost();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<title>IOTPrint订单信息</title>
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


	.payActive{
		width:14%;
		border:2px solid red;
	}
	.payNegative{
		width:10%;
		border:1px solid blue;
	}
</style>
<script type="text/javascript">
	function switch1(list_x) {
		var list0 = document.getElementsByClassName("payNegative");
		var list1 = document.getElementsByClassName("payActive")[0];
		for (var i = 0; i <= list0.length; i++) {
			var j = i;
			var m = i;
			if (list0[i] == list_x) {
				for (j = 0; j <= i; j++) //居然不适用也可以..要死
				{
					if (list0[j] == null) {
						m--;
					}
				}
				list0[i].setAttribute("class", "payActive");
				list1.setAttribute("class", "payNegative");
			}
		}
	}
	//检验收货信息表格是否为空
	function checkFormNotNull() {
		var consignee = document.getElementById("consignee").value;
		var phoneNum = document.getElementById("phoneNum").value;
		var address = document.getElementById("address").value;
		
		if (consignee=="" || phoneNum=="" || address=="") {
			alert("请填写必要的收货信息！");
			return false;
		}
		return true;
	}
</script>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
		<div class="container">
		<div class="navbar-header">
			<a href="#" class="navbar-brand"
				style="margin: 0; padding: 0;"><img src="./img/userOrder.jpg"
				alt="LOGO"></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div id="welcome1">
			<p id="welcome2">
				<strong>欢迎：<%=userName %></strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
		</div>
		<br />
		</div>
	</nav>
	<div style="width:100%;height:105px;"></div>
	
	<div style="width:900px;height:920px;margin:0 auto;border:2px solid #DCDCDC;border-radius:5px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;">
		<div style="width:100%;height:20px;"></div>
	<form action="SubmitOrderServlet.do" method="post" onSubmit="return checkFormNotNull();">	
		<div style="width:95%;height:90%;margin:0 auto;">
			<div style="width:95%;height:252px;margin:0 auto;">
				<div style="width:98%;height:30px;margin:0 auto;margin-top:20px;">
					<label for="收货信息" style="font-size:18px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:200px;color:blue;">收货信息（*为必填项）</label>
				</div>
				<div style="width:98%;height:3px;margin:0 auto;"><hr style="width:90%;size:10;"/></div>
				<div style="height:150px;width:80%;margin:0 auto;margin-top:10px;">
					<div style="height:50px;">
						<div style="float:left;"><label for="收件人" style="font-size:15px;width:100px;margin-top:6px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:100px;"><B>收&nbsp;&nbsp;件&nbsp;人：*</B></label></div><div style="float:left;"><textarea class="form-control" name="consignee" id="consignee" rows="1" style="height:35px;width:500px;"><%=userName %></textarea></div>
					</div>
					<div style="height:50px;">
						<div style="float:left;"><label for="手机号码" style="font-size:15px;width:100px;margin-top:6px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:100px;"><B>手机号码：*</B></label></div><div style="float:left;"><textarea class="form-control" name="phoneNum" id="phoneNum" rows="1" style="height:35px;width:500px;"><%=phoneNum %></textarea></div>
					</div>
					<div  style="height:50px;">
						<div style="float:left;"><label for="收件地址" style="font-size:15px;width:100px;margin-top:6px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:100px;"><B>收件地址：*</B></label></div><div style="float:left;"><textarea class="form-control" name="address" id="address" rows="1" style="height:35px;width:500px;"><%=address %></textarea></div>
					</div>
					<div style="height:50px;">
						<div style="float:left;"><label for="备注信息" style="font-size:15px;width:100px;margin-top:6px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:100px;"><B>备注信息：</B></label></div><div style="float:left;"><textarea class="form-control" name="message" id="consignee" rows="1" style="height:35px;width:500px;"></textarea></div>
					</div>
				</div>
			</div>
			<div style="width:95%;height:370px;margin:0 auto;margin-top:50px;">
				<div style="width:98%;height:30px;margin:0 auto;margin-top:20px;">
					<label for="OrderDetail" style="font-size:18px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:100px;color:blue;">订单详情</label>
				</div>
				<div style="width:98%;height:3px;margin:0 auto;"><hr style="width:90%;size:10;"/></div>
				<div style="height:290px;width:80%;margin:0 auto;margin-top:10px;">
					<table class="table">
					   <thead>
					      <tr style="font-size:16px;text-align:center;">
					         <th style="width:70px;text-align:center;">序号</th>
					         <th style="width:300px;text-align:center;">文件名</th>   <!-- /*截取 字符串的一定长度*/ -->
					         <th style="width:80px;text-align:center;">页数</th>
					         <th style="width:120px;text-align:center;">单面/双面</th>
					         <th style="width:100px;text-align:center;">打印份数</th>
					      </tr>
					   </thead>
					   <tbody font-size="14px">
					   <%for(int i=0;i<fileNum;i++){ %>
					      <tr style="font-size:14px;text-align:center;">
					         <td style="width:70px;height:60px;text-align:center;padding-top:20px;"><%=i+1 %></td>
					         <td style="width:300px;font-size:14px;text-align:center;padding-top:20px;"><%=userPrintInfos.getInfo(i).getFileName() %></td>
					         <td style="width:100px;text-align:center;padding-top:20px;"><%=userPrintInfos.getInfo(i).getPages() %>页</td>
					         <td style="width:100px;text-align:center;padding-top:20px;">单面</td>
					         <td style="width:80px;text-align:center;padding-top:20px;"><%=userPrintInfos.getInfo(i).getCopies() %>份</td>
					         <!-- <th style="width:80px;">100.9元</th> -->
					      </tr>
					   <%} %>
					   </tbody>
					</table>
					<div style="margin-top:20px;">
						<!-- <div style="float:left;"><label for="备注信息" style="font-size:15px;width:100px;margin-top:6px;font-family:'微软雅黑','隶书','宋体',Helvetica,Helvetica,Arial;width:100px;"><B>备注信息</B></label></div><div style="float:left;"><textarea class="form-control" rows="1" style="height:35px;width:500px;"></textarea></div> -->
					</div>
				</div>
			</div>
			<div style="width:95%;height:90%;margin:0 auto;margin-top:80px;">
			<hr style="width:90%;size:10;"/>
				<div style="background-color:blue;">
					<div style="width:25%;float:left;height:50px;"></div>
					<div style="width:46%;float:left;padding-top:10px;height:50px;border-radius:5px;color:black;"><span style="font-size:20px;text-align:center;"><b>总价：&nbsp;&nbsp;&nbsp;<%=cost %>元</b></span></div>
					<div style="width:3%;float:left;height:50px;"></div>
				    <div style="float:left;width:17%;height:50px;"><button type="submit" class="btn btn-default btn-lg" style="background-color:red;color:white;">立即下单</button></div>
				</div>
			</div>
	</form>
		</div>
	</div>
	<div style="width:100%;height:100px;"></div>
</body>
</html>