<%@page import="com.Atschool.JavaBean.UserPrintInfoAll"%>
<%@page import="com.Atschool.Class.LogInfo"%>
<%@page import="com.Atschool.JavaBean.UserPrintInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Atschool.JavaBean.UserOrderInfo"%>
<%@page import="com.Atschool.Class.SQLOperate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./WEB-INF/jsp/checkLogin.jsp"%>
<%
	String userName=(String)session.getAttribute("userName");
	String fileName="";
	double costEach=0;
	String address="";
	String submitOrderTime="";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11">
<title>IOTPrint订单信息</title>
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
	text-align: center;
	font-size: 14px;
	font-family: 'Source Sans Pro', 'Helvetica Neue', 'Helvetica', 'Arial',
		'sans-serif';
}

#welcome2 {
	text-align: center;
	padding-top: 20px;
}

#interval00 {
	height: 61px;
	background-color: #F0F0F0;
}

.content_active {
	display: block;
}

.content_negative {
	display: none;
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

	<!-- HTML页面布局 -->
	<div id="divContainer">
		<div class="main">
			<div class="list_active" id="PrintDocTAB" onclick="switch1(this)">
				<span>文&nbsp;&nbsp;&nbsp;档</span>
			</div>
			<div class="list_negative" id="PrintImgTAB" onclick="switch1(this)">
				<span>照&nbsp;&nbsp;&nbsp;片</span>
			</div>
			<div class="clear"></div>

			<div class="wrap">
				<div id="allWordList" class="content_active" style="padding: 5px;">
					<!-- 文档 -->
					<div style="padding: 5px; margin-top: 5px;">
						<table id="tabdoc" class="border_tab2" width="100%">
							<tbody>
								<tr>
									<th width="6%" height="46"></th>
									<th width="24%" height="46" style="text-align: left;">文件名称</th>
									<th width="10%" height="46">费用</th>
									<th width="19%" height="46">收货地址</th>
									<th width="20%" height="46">提交时间</th>
									<th width="5%" height="46">状态</th>
									<th width="10%" height="46">操作</th>
								</tr>
							<%
								if(userName!=null){
									for(int ii=0;ii<=4;ii++){//------------通过循环获取所有的订单信息--------------
										ArrayList<UserOrderInfo> userOrderInfos=new ArrayList<UserOrderInfo>();
										if(userName.equals("TestID")){//测试账号的订单信息从session中获取
											ii=1000;//不需要再遍历所有订单了
											UserOrderInfo tempUserOrderInfo=(UserOrderInfo)session.getAttribute("userOrderInfo");
											if(tempUserOrderInfo!=null){//订单信息可能为空
												userOrderInfos.add(tempUserOrderInfo);
											}
										}else{
											userOrderInfos=SQLOperate.getUserOrderInfo(ii);
										}
										for(int i=0;i<userOrderInfos.size();i++){ //-------遍历所有订单-----------
											submitOrderTime=userOrderInfos.get(i).getSubmitOrderTime();
											address=userOrderInfos.get(i).getAddress();
											submitOrderTime=userOrderInfos.get(i).getSubmitOrderTime();
											String orderState=null;
											switch(userOrderInfos.get(i).getOrderState()){
											case 0:
												orderState="待付款";
												break;
											case 1:
												orderState="待打印";
												break;
											case 2:
												orderState="即将打印";
												break;
											case 3:
												orderState="打印完成";
												break;
											case 5:
												orderState="已收货";
												break;
											default:
												break;
											}
											
											ArrayList<UserPrintInfo> userPrintInfos=new ArrayList<UserPrintInfo>();
											if(userName.equals("TestID")){//测试账号的文件信息从session中获取
												UserPrintInfoAll tempUserPrintInfos=(UserPrintInfoAll)session.getAttribute("userPrintInfos");
												if(tempUserPrintInfos!=null){
													for(int iii=0; iii<tempUserPrintInfos.getFileNum();iii++){
														userPrintInfos.add(tempUserPrintInfos.getInfo(iii));
													}
												}
											}else{
												userPrintInfos=SQLOperate.getUserPrintInfo(userName, submitOrderTime);
											}
											for(int j=0;j<userPrintInfos.size();j++){//-----------遍历所有文件--------------
												fileName=userPrintInfos.get(j).getFileName();
												costEach=userPrintInfos.get(j).getCostEach();
							%>
								<tr>
									<td style="text-align: center;" height="46"><input
										class="orderNo" type="checkbox" style="display: none"
										value="73e40529-f502-4082-beb6-05e1274f03e9"></td>
									<td style="text-align: left;" height="46" name="文件名"><%=fileName %></td>
									<td style="text-align: center;" height="46" name="费用"><%=costEach %>元</td>
									<td style="text-align: center;" height="46" name="收货地址"><%=address %></td>
									<td style="text-align: center;" height="46" name="提交时间"><%=submitOrderTime %></td>
									<td style="text-align: center;" height="46" name="状态"><%=orderState %></td>
									<td style="text-align: center;" valign="middle" height="46">
										<!-- <button type="button" class="btn btn-default btnCancel"
											onclick=""
											style="color: red; font-size: 14px; width: 80px; height: 30px; background-color: white;">取消订单</button> -->
									</td>
								</tr>
							<%}}}} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="wrap">
				<div id="allImgList" class="content_negative" style="padding: 5px;">
					<!-- 照片 -->
					<div style="padding: 5px; margin-top: 5px;">
						<table id="tabdoc" class="border_tab2" width="100%">
							<tbody>
								<tr>
									<th width="6%" height="46"><input style="display: none;"
										class="check-all" onclick="doCheckAll(this)" type="checkbox"></th>
									<th width="20%" height="46" style="text-align: left;">文件</th>
									<th width="10%" height="46">费用</th>
									<th width="29%" height="46">学校</th>
									<th width="20%" height="46">提交时间</th>
									<th width="5%" height="46">状态</th>
									<th width="10%" height="46">操作</th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 主要内容END -->

	<script type="text/javascript">
		function switch1(list_x) {
			var list0 = document.getElementsByClassName("list_negative");
			var list1 = document.getElementsByClassName("list_active")[0];
			var content0 = document.getElementsByClassName("content_negative");
			var content1 = document.getElementsByClassName("content_active")[0];
			for (var i = 0; i <= list0.length; i++) {
				var j = i;
				var m = i;
				if (list0[i] == list_x) {
					for (j = 0; j <= i; j++) //居然不适用也可以..要死
					{
						if (list0[j] == null) {
							m--;
						}
						if (content0[j] == null) {
							m++;
						}
					}
					list0[i].setAttribute("class", "list_active");
					list1.setAttribute("class", "list_negative");

					content0[m].setAttribute("class", "content_active");
					content1.setAttribute("class", "content_negative");

				}
			}
		}
	</script>


	<script src="./js/jquery-2.2.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>
