<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="checkLogin.jsp"%>
<%String userName=(String)session.getAttribute("userName");%>

<!DOCTYPE html>
<!-- saved from url=(0016)http://yindd.cn/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11">

<title>IOTPrint照片打印</title>

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />
<link rel="stylesheet" href="./css/userUpload.css">
<script type="text/javascript"
	src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
<style>
body {
	margin: 0px auto;
	background-color: #F4F4F4;
}

#interval0 {
	height: 115px;
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
	margin: 10px auto;
	display: block;
	float: bottom;
	width: 800px;
}

.fileInput {
	height: 56px;
	overflow: hidden;
	font-size: 17px;
	position: absolute;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
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
	window.onload = function() {
		// 鼠标移动改变背景,可以通过给每行绑定鼠标移上事件和鼠标移除事件来改变所在行背景色。
		var tr = document.getElementsByTagName("tr");
		for (var i = 0; i < tr.length; i++) {
			bgc(tr[i]);
		}
	}
	function bgc(obj) {
		obj.onmouseover = function() {
			obj.style.backgroundColor = "#CCC";
		}
		obj.onmouseout = function() {
			obj.style.backgroundColor = "white";
		}

	}

	// 编写一个函数，供添加按钮调用，动态在表格的最后一行添加子节点；
	var num = 2;
	function add1() {
		// var fileName1 = prompt("请输入文件名:");
		// if(fileName1 != null)
		{
			// var filePage = prompt("请输入文件页数:");
			// if(filePage != null)
			{
				// var printPage = prompt("请输入打印页数:");
				// if(printPage != null)
				{
					num++;
					var table1 = document.getElementById("tablePrint1")
					var newlast = document.createElement("tr");
					var th1 = document.createElement("th");
					th1.innerHTML = "fileName1";
					var th2 = document.createElement("th");
					th2.innerHTML = "filePage";
					var th3 = document.createElement("th");
					th3.innerHTML = "printPage";
					var th4 = document.createElement("th");
					th4.innerHTML = "<select><option selected=selected value=1>黑白</option><option value=2>彩色</option></select>";
					var th5 = document.createElement("th");
					th5.innerHTML = "<select><option selected=selected value=1>单面</option><option value=2>双面</option></select>";
					var th6 = document.createElement("th");
					th6.innerHTML = "11";
					var th7 = document.createElement("th");
					th7.innerHTML = "<a href='javascript:;' onclick='remove1(this);'>预览</a>"
							+ ' '
							+ "<a href='javascript:;' onclick='remove1(this);'>删除</a>"
					table1.appendChild(newlast);
					newlast.appendChild(th1);
					newlast.appendChild(th2);
					newlast.appendChild(th3);
					newlast.appendChild(th4);
					newlast.appendChild(th5);
					newlast.appendChild(th6);
					newlast.appendChild(th7);

					var tr = document.getElementsByTagName("tr");
					for (i = 0; i < tr.length; i++) {
						bgc(tr[i]);
					}
				}
			}
		}
	}

	// 创建删除函数
	function remove1(obj) {
		num--;
		var tmp = obj.parentNode.parentNode;
		tmp.parentNode.removeChild(tmp);
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
					<strong>欢迎您！</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
							class="glyphicon glyphicon-list-alt"></span> <font
							color="#92a8d1"><B>我的订单</B></font></a></li>
					<li><a href="./userWallet.jsp"><span
							class="glyphicon glyphicon-tag"></span> <font color="#92a8d1"><B>我的钱包</B></font></a></li>
					<li><a href="./userInfo.jsp"><span
							class="glyphicon glyphicon-comment"></span> <font color="#92a8d1"><B>个人信息</B></font></a></li>
					<li><a href="./userNotice.jsp"><span
							class="glyphicon glyphicon-envelope"></span> <font
							color="#92a8d1"><B>我的通知</B></font></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="interval0"></div>
	<!--主要内容-->
	<div id="divContent">

		<div class="main" id="main111">
			<div class="wrap">
				<div id="docCatSpanDIV">
					<div class="dataMore">
						<div class="uploading">
							<div class="upleft">

								<input class="fileInput" type="file" name="" id=""
									onclick="add1()">上传/继续上传图片</input>
							</div>
							<div class="upright">
								<span>选择需要打印的文件，可批量上传（支持：doc/docx、ppt/pptx、pdf、jpg、bmp、png、gif、jpeg）
								</span>
							</div>

							<div class="clear"></div>
						</div>

						<div class="upcontent">
							<div class="h20"></div>
							<table width="100%" border="0" cellspacing="0"
								class="border_tab2" id="tablePrint1">
								<tbody>
									<tr>
										<th width="24%">文件名称</th>
										<th width="9%">页数</th>
										<th width="13%">打印页数</th>
										<th width="10%">黑白/彩色打印</th>
										<th width="12%">单面双面</th>
										<th width="12%">打印份数</th>
										<th width="20%">操作</th>
									</tr>
									<tr style="display: none;" id="fileViewTR">
										<td colspan="9" style="border: 0px;"><span
											id="fileViewSpan"></span></td>
									</tr>

								</tbody>
							</table>
							<table>
								<tbody>
									<tr>
										<td height="50" colspan="6"
											style="text-align: right; font-size: 16px; font-weight: bold;"></td>
										<td height="50" style="text-align: center;"><input
											type="button" id="SubDocBtn" value="提交订单" class="submitOrder"
											onclick="docCatSubmit()"
											style="color: black; text-decoration: none; outline: none; border: none;">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="dataNone" style="display: none;">
						<input type="button" class="btn_upload" value="上传文件"
							onclick="checkUpload();">

					</div>
					<input id="CatListCount" name="CatListCount" type="hidden"
						value="1"> <input id="CatDcoumentListCount"
						name="CatDcoumentListCount" type="hidden" value="1">


				</div>
			</div>
			<form action="#" enctype="multipart/form-data" id="upform"
				method="post" name="upform">
				<input type="file" name="selectFile" id="selectFile"
					style="visibility: hidden" multiple="multiple"
					accept=".doc,.docx,.ppt,.pptx,.pdf,.png,.jpg,.jpeg,.gif,.bmp"
					onchange="filesUpload()">
			</form>
		</div>
	</div>
	<!-- 主要内容END -->
	<div class="footer">南京邮电大学自动打印小组 ©
		&amp;请使用IE11、Chrome、Firefox、360浏览器访问</div>
	<div class="theme-popover-mask"></div>
	<!--遮盖层-->

</body>
</html>