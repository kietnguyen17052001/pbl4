<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<title>Change password</title>
<link rel="stylesheet" href="css/ForgotPassword.css" type="text/css" />
<script type="text/javascript" src="js/Login_Registration.js"></script>
<script type="text/javascript" src="js/Exception.js"></script>
</head>
<body>
	<%
	int userId = (int) request.getAttribute("userId");
	%>
	<div class="change-password">
		<div class="title">Please enter full information to change
			password</div>
		<div>
			<form name="formChangePassword"
				action="User_Controller?type=changePassword&subType=changePasswordForgot&userId=<%=userId%>"
				method="post">
				<input id="input-oldpassword" type="password"
					placeholder="Enter old password" name="oldpassword"> <i
					id="eye-oldpassword" class="fas fa-eye-slash"
					onclick="togglePassword('oldPassword')"></i> <input
					id="input-newpassword" type="password"
					placeholder="Enter new password" name="newpassword"> <i
					id="eye-newpassword" class="fas fa-eye-slash"
					onclick="togglePassword('newPassword')"></i> <input
					id="input-confirmpassword" type="password"
					placeholder="Confirm new password" name="confirmpassword">
				<i id="eye-confirmpassword" class="fas fa-eye-slash"
					onclick="togglePassword('confirmPassword')"></i> <input
					type="submit" value="Confirm"
					onclick="return checkChangePassword()">
			</form>
		</div>
	</div>
</body>
</html>