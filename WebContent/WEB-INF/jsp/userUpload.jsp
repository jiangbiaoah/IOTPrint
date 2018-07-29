<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@page import="com.Atschool.JavaBean.UserPrintInfoAll"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileUpload"%>
<%@ page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="checkLogin.jsp"%>
<%
	session.setAttribute("once", "true");
	String userName = (String) session.getAttribute("userName");
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11">

<title>IOTPrint文档打印</title>

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./img/icon.jpg" rel="shortcut icon" />
<link rel="stylesheet" href="./css/userUpload.css">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
<style>
body {
	margin: 0px auto;
	background-color: #F4F4F4;
}

#interval0 {
	height: 85px;
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
	//检验文件是否符合要求：1.文件不能为空。 2.文件类型要符合要求
	function checkFormNotNull() {
		if (document.getElementById("selectFile").value == "") {
			alert("请选择文件");
			return false;
		}
		return true;
	}
	function checkFile(target,id){
		var fileSize = 0;
		var filetypes =[".txt",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf"];
		var filepath = target.value;
		var filemaxsize = 1024*2;
		if(filepath){
			var isnext = false;
			var fileend = filepath.substring(filepath.indexOf("."));
			if(filetypes && filetypes.length>0){
				for(var i =0; i<filetypes.length;i++){
					if(filetypes[i]==fileend){
						isnext = true;
						break;
					}
				}
			}
			if(!isnext){
				alert("不支持打印该格式的文件，请重新选择！");
				target.value ="";
				return false;
			}
		}
		else{
			return false;
		}
		if (isIE && !target.files) {
			var filePath = target.value;
			var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
			if(!fileSystem.FileExists(filePath)){
				alert("文件不存在，请重新输入！");
				return false;
			}
			var file = fileSystem.GetFile (filePath);
			fileSize = file.Size;
		}
		else {
			fileSize = target.files[0].size;
		}
		var size = fileSize/1024;
		if(size>filemaxsize){
			alert("文件不能大于"+filemaxsize/1024+"M！");
			target.value ="";
			return false;
		}
		if(size<=0){
			alert("文件大小不能为0M！");
			target.value ="";
			return false;
		}
		
	}
</script>


</head>
<body style="cursor: default;">
	<jsp:useBean id="userPrintInfos" scope="session"
		class="com.Atschool.JavaBean.UserPrintInfoAll">
	</jsp:useBean>
	<nav class="navbar navbar-default navbar-fixed-top" id="nav">
		<div class="container">
			<div class="navbar-header">
				<a href="./index.jsp" class="navbar-brand"
					style="margin: 0; padding: 0;"><img src="./img/LR.png"
					alt="LOGO"></a>
			</div>
			<div id="welcome1">
				<p id="welcome2">
					<strong>欢迎您:<%=userName%></strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
						<div>
							<!-- 删除了class="uploading" -->
							<div class="upright"
								style="width: 80%; height: 30px; margin: 0 auto;">
								<span>上传需要打印的文件（支持：txt、doc/docx、xls/xlsx、ppt/pptx、pdf） </span>
							</div>
							<div class="upleft" style="width: 70%; height: 30px;">
								<!-- servlet上传文档 -->
								<!-- input type="file"中删除了class="fileInput"  -->
								<form action="./UploadFile.do" method="post" enctype="multipart/form-data" onSubmit="return checkFormNotNull();" style="width: 90%; margin: 0 auto;">
									<div style="width: 80%; float: left;">
										<input type="file" name="userUploadFile" id="selectFile" onchange="checkFile(this);" />
									</div>
									<div style="width: 19%; float: left;">
										<input type="submit" name="upload" value="上传"/>
									</div>
									<%
										session.setAttribute("userPrintInfos", userPrintInfos);
									%>
								</form>
							</div>
							<div class="clear"></div>
						</div>
						<div class="upcontent">
							<div class="h20"></div>
							<table width="100%" border="0" cellspacing="0"
								class="border_tab2" id="tablePrint1">
								<tbody style="text-align: center;">
									<tr>
										<th width="24%">文件名称</th>
										<th width="9%">页数</th>
										<th width="10%">黑白/彩色打印</th>
										<th width="12%">单面双面</th>
										<th width="12%">打印份数</th>
										<th width="13%">打印费用</th>
										<th width="20%">操作</th>
									</tr>
									<tr style="display: none;" id="fileViewTR">
										<td colspan="9" style="border: 0px;"><span
											id="fileViewSpan"></span></td>
									</tr>
								<%for(int i=0;i<userPrintInfos.getFileNum();i++){ %>
									<tr>
										<td><%=userPrintInfos.getInfo(i).getFileName()%></td>
										<td><%=userPrintInfos.getInfo(i).getPages()%></td>
										<td>黑白</td>
										<td>单面打印</td>
										<td><%=userPrintInfos.getInfo(i).getCopies()%></td>
										<td><%=userPrintInfos.getInfo(i).getCostEach()%>元</td>
										<td>
											<a href="PreviewDocServlet.do?id=<%=i %>" target="_blank">预览</a>
											<a>/</a>
											<a href="DeleteDocServlet.do?id=<%=i %>">删除</a>
										</td>
									</tr>
								<%} %>
								</tbody>
							</table>
							<table width="100%" border="0" cellspacing="0">
								<tbody width="100">
									<tr>
										<td height="50" colspan="6"
											style="text-align: center; font-size: 16px; font-weight: bold; width: 80%;">
											文件总数：<%=userPrintInfos.getFileNum()%>份 总费用：<%=userPrintInfos.getCost()%>元
										</td>
										<td height="50" style="text-align: center; width: 18%;">
											<a href="receivingInfo.action"> <input type="button"
												id="SubDocBtn" value="去结算" class="submitOrder"
												style="color: black; text-decoration: none; outline: none; border: none;" /></a>
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
			<form action="./userUpload.jsp" enctype="multipart/form-data"
				id="upform" method="post" name="upform">
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