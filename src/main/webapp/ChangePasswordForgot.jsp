<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<link rel="stylesheet" href="css/ForgotPassword.css" type="text/css" />
</head>
<body>
	<%
	int userId = (int) request.getAttribute("userId");
	%>
	<div class="change-password">
		<div class="title">Please enter full information to change
			password</div>
		<div>
			<form
				action="User_Controller?type=changePassword&subType=changePasswordForgot&userId=<%=userId%>"
				method="post">
				<input type="password" placeholder="Enter old password"
					name="oldpassword"> <input type="password"
					placeholder="Enter new password" name="newpassword"> <input
					type="password" placeholder="Confirm new password"
					name="confirmpassword"> <input type="submit"
					value="Confirm">
			</form>
		</div>
	</div>
</body>
</html>