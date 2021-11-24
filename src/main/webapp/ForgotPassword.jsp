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
</head>
<body>
	<div class="forgot-password">
		<div class="title">Please enter full information to verify</div>
		<div>
			<form action="User_Controller?type=forgotPassword" method="post">
				<input type="email" placeholder="Enter your email" name="email">
				<input type="tel" placeholder="Enter your phone number" name="phone">
				<input type="submit" value="Confirm">
			</form>
		</div>
	</div>
</body>
</html>