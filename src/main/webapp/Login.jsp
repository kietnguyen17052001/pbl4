<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<title>SugarApp</title>
<link rel="stylesheet" href="css/Login.css" type="text/css" />
<script type="text/javascript" src="js/Login_Registration.js"></script>
<script type="text/javascript" src="js/Exception.js"></script>
</head>
<body>
	<%
	String username = (String) request.getAttribute("username");
	String password = (String) request.getAttribute("password");
	if (username == null && password == null) {
		username = password = "";
	}
	%>
	<div class="container">
		<div class="left">
			<img src="image/Dating-Apps.png" alt="image">
		</div>
		<div class="right">
			<div class="top-form">
				<p class="name-app">SUGAR APP</p>
				<div class="form-login">
					<form name="formLogin" action="User_Controller?type=homePage"
						method="post">
						<input id="username" type="text" name="username"
							placeholder="Username, phone number or email"
							value="<%=username%>"> <input id="input-password"
							type="password" name="password" placeholder="Password"
							value="<%=password%>"><i id="eye"
							class="fas fa-eye-slash" onclick="togglePassword('password')"></i><input
							type="submit" value="Log in" onclick="return checkLogin()">
					</form>
				</div>
				<div id="failed" style="text-align: center; color: red;"></div>
				<div class="forgot-password">
					<a class="forgot-password" href="ForgotPassword.jsp">Forgot
						password?</a>
				</div>
			</div>
			<div class="bottom-form">
				<p>
					You don't have an account yet? <a href="Registration.jsp"
						class="sign-up">Register</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>