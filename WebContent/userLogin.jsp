<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userName=(String)session.getAttribute("userName");
	if(userName==null){
		userName="";
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="./css/userLogin.css">
<link rel="stylesheet" href="./css/userRegistration.css">
<script type="text/javascript" src="./userRegistration.js"></script>
<style>
.auth {
	margin: 0 auto;
	width: 500px;
	/*list-style-position: center;*/
}
</style>
</head>
<title>IOTPrint用户登录</title>
<body class="app auth">
	<div class="auth">
		<div >
			<header class="global-header">
				<div width="100%">
					<nav class="global-nav">
						<a href="./userLogin.jsp" class="logo"><img
							src="./img/IOTPrint.png" width="150"></a>
					</nav>
				</div>
			</header>
			<div class="global-content" style="width:700px; margin:0 auto;background-color:white;">
				<form action="LoginServlet.do" method="post"
					onsubmit="return userLogin(this);">
					<h1 align="center"></h1>
					<h1 align="center">
						登录
						</h2>
						<div class="form-heading">使用IOTPrint帐号</div>
						<div class="error" style="display: none;">登录失败 ，请仔细核对你的帐号与密码
						</div>
						<fieldset class="input-group">
							<input id="userName" name="userName" value="<%=userName %>" placeholder="&nbsp昵称" style="height:45px;font-size:16px;">
							<label class="error" style="display: none;height:44px;">请输入昵称</label>
						</fieldset>
						<fieldset class="input-group">
							<input type="password" name="password" placeholder="密码">
							<label for="username-input" class="error" style="display: none;">请输入密码</label>
							<label for="password-input" class="error" style="display: none;">请输入至少6个字符</label>
						</fieldset>
						<input id="csrf" name="csrf" type="hidden">
						<div class="button-container">
							<button class="button submit" type="submit" value="send">登录 <span class="spinner"></span>
							</button>
							<a class="btn-secondary" href="./userRegistration.jsp"> 注册</a></br>
							<a class="btn-secondary" href="./LoginTestServlet.do"> 体验账号登陆</a>
						</div>
						<div class="forgot-container"
							style="float: left; font-size: 15px;">其他登录方式：</div>
						<div class="forgot-container"
							style="float: right; font-size: 13px;">
							<a href="#">忘记密码</a>
						</div>
						<br /> <br />
						<div class="connect-options" style="margin:0 auto;width:80px;">
							<!-- <button
								onclick="location.href=&quot;https://github.com/login/oauth/authorize?client_id=fce60cc700d12676fbe1&amp;redirect_uri=http://login.codevs.com/auth/callback/github/&amp;scope=user:email&amp;state=wph95isgoodman&quot;"
								id="github_login" title="github" type="button"
								class="github-auth">
								<span class="auth-container"> <img
									src="./CodeVS - 登录_files/github.png" alt="github">
								</span>
							</button> -->
							<button id="qq_login" title="qq" type="button" class="qq-auth" style="align:center;">
								<span class="auth-container"> <img
									src="./img/qq.png" alt="qq" style="margin-top:-5px;margin-left:-3px;">
								</span>
							</button>
						</div>
				</form>
			</div>
		</div>
		<footer>
			<div class="text-smaller text-white">
				<strong>领先的自动打印平台</strong> <span class="bullet">IOTPrint ©
					2016-2017 </span><span class="bullet"></span>苏备号 <br> <a
					class="link-white">关于</a> <span class="bullet"></span> <a
					class="link-white">API</a> <span class="bullet"></span> <a
					href="mailto:wph95@codevs.cn" class="link-white">商务合作</a> <span
					class="bullet"></span> <a class="link-white">帮助</a> <span
					class="bullet"></span> <a class="link-white">团队</a> <span
					class="bullet"></span> <a class="link-white">隐私</a>
			</div>
		</footer>
	</div>
	<div id="progress" class=" "></div>
	<!-- //7xp5ik.com1.z0.glb.clouddn.com/account/ -->



</body>
</html>