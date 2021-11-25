<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot password</title>
<link rel="stylesheet" href="css/ForgotPassword.css" type="text/css" />
<script type="text/javascript" src="js/Exception.js"></script>
</head>
<body>
	<div class="forgot-password">
		<div class="title">Please enter full information to verify</div>
		<div>
			<form name="formForgotPassword"
				action="User_Controller?type=forgotPassword" method="post">
				<input id="email" type="email" placeholder="Enter your email"
					name="email"> <input id="phone" type="tel"
					placeholder="Enter your phone number" name="phone"
					pattern=[0-9]{10}> <input type="submit" value="Confirm"
					onclick="return checkForgotPassword()">
			</form>
		</div>
	</div>
</body>
</html>