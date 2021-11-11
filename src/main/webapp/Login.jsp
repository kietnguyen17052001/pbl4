<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập Sugar App</title>
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
					<form action="User_Controller?type=login" method="post">
						<input type="text" name="username_phone_mail"
							placeholder="Tên người dùng, email hoặc số điện thoại"> <input
							type="password" name="password" placeholder="Mật khẩu"> <input
							type="submit" value="Đăng nhập">
					</form>
				</div>
				<div class="forgot-password">
					<a class="forgot-password" href="#">Quên mật khẩu?</a>
				</div>
			</div>
			<div class="bottom-form">
				<p>
					Bạn chưa có tài khoản ư? <a href="Registration.jsp" class="sign-up">Đăng
						ký</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>