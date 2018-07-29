<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="./css/userRegistration.css">
<script type="text/javascript" src="./userRegistration.js"></script>
</head>
<title>IOTPrint用户注册</title>
<style>
#phoneNum1 {
	height: 41px;
	font-size: 15px;
}
</style>
<body class="app auth">
	<div class="auth">
		<div style="margin-bottom: 10px">
			<header class="global-header">
				<div>
					<nav class="global-nav">
						<a href="#" class="logo"> <img src="./img/IOTPrint.png" width="150">
						</a>
					</nav>
				</div>
			</header>
			<div class="global-content">
				<section class="login fade-transition">
					<form action="RegServlet.do" method="post"
						onsubmit="return userRegistration(this);">
						<h1>注册</h1>

						<div class="form-heading">注册IOTPrint帐号</div>
						<div class="error" style="display: none;">
							注册失败， <span style="display: none;">昵称</span> <span
								style="display: none;">邮箱</span> 已经被注册。<br> 可<a
								class="error_link" href="#">直接登录</a>。
						</div>
						<fieldset class="input-group">
							<input type="text" name="userName" placeholder="昵称"> <label
								class="error" style="display: none;">请填写你的昵称</label>
						</fieldset>
						<fieldset class="input-group">
							<input type="email" name="email" placeholder="邮箱"> <label
								class="error" style="display: none;">请填写你的邮箱</label> <label
								class="error" style="display: none;">请输入一个正确的邮箱</label>
						</fieldset>
						<fieldset class="input-group">
							<input id="phoneNum1" name="phoneNum" placeholder="手机号">
							<label class="error phoneNum" style="display: none;">请填写你的手机号</label>
							<label class="error" style="display: none;">请输入一个正确的手机号</label>
						</fieldset>
						<fieldset class="input-group">
							<input type="password" name="password" placeholder="密码">
							<label for="username-input" class="error" style="display: none;">请填写你的密码</label>
							<label for="password-input" class="error" style="display: none;">请输入至少6个字符</label>
						</fieldset>
						<fieldset class="input-group">
							<input type="password" name="repassword" placeholder="确认密码">
							<label for="username-input" class="error confirmPassword"
								style="display: none;">请填写你的确认密码</label> <label
								for="password-input" class="error" style="display: none;">请保持与上面的密码一致</label>
						</fieldset>
						<fieldset class="input-group">
							<input id="phoneNum1" name="PIN" placeholder="邀请码">
							<label class="error phoneNum" style="display: none;">请填写邀请码</label>
							<label class="error" style="display: none;">请输入一个正确的邀请码</label>
						</fieldset>
						<div class="button-container">
							<button class="button submit" type="submit" value="send">
								注册 <span class="spinner"></span>
							</button>
							<a id="login" class="btn-secondary" href="./userLogin.jsp">
								已经有帐号？ </a>
						</div>
						<div class="acceptance-container">
							在注册之前， 你需要了解并同意 IOTPrint <br> <a target="_blank">用户使用协议</a>
							<a href="http://login.codevs.com/auth/register?next=http%253A%252F%252Flogin.codevs.com%252Fauth%252Fsocials%252Fbind%252F%253Faccess_token%253DFED1026FADD2ADEDC6AA322DCF36480F%2526provider%253Dqq&noToken=true#"
								target="_blank"></a>,<a target="_blank">隐私保护协议</a>.
						</div>
					</form>
				</section>
			</div>
		</div>
		<footer>
			<div class="text-smaller text-white">
				<strong>领先的自动打印平台</strong> <span class="bullet">© 2016-20** </span><span
					class="bullet"></span>苏ICP备0000号 <br> <a class="link-white">关于</a>
				<span class="bullet"></span> <a class="link-white">API</a> <span
					class="bullet"></span> <a href="mailto:wph95@codevs.cn"
					class="link-white">商务合作</a> <span class="bullet"></span> <a
					class="link-white">帮助</a> <span class="bullet"></span> <a
					class="link-white">团队</a> <span class="bullet"></span> <a
					class="link-white">隐私</a>
			</div>
		</footer>
	</div>
	<div id="progress" class=" "></div>
</body>
</html>