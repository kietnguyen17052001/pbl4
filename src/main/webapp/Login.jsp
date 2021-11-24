<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SugarApp</title>
<link rel="stylesheet" href="css/Login.css" type="text/css" />
<script src="js/Login_Registration.js"></script>
</head>
<body>
	<div class="container">
		<div class="left">
			<img src="image/Dating-Apps.png" alt="image">
		</div>
		<div class="right">
			<div class="top-form">
				<p class="name-app">SUGAR APP</p>
				<div class="form-login">
					<form name="formLogin" action="User_Controller?type=login"
						method="post">
						<input type="text" name="username"
							placeholder="Username, phone number or email"> <input
							type="password" name="password" placeholder="Password"> <input
							type="submit" value="Log in" onclick="checkLogin()">
					</form>
				</div>
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