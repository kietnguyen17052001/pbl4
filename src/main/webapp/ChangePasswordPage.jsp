<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<title>Sugar App</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("user");
	int userId = user.getUser_id();
	String name = user.getLast_name() + " " + user.getFirst_name();
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=home&userId=<%=userId%>"
					target="top-main">Suger App</a>
			</div>
			<div class="search">
				<input type="text" placeholder="Search">
			</div>
			<div class="option">
				<ul>
					<li class="home"><a
						href="User_Controller?type=homePage&userId=<%=userId%>"><i
							class="fas fa-home"></i></a></li>
					<li class="message"><a
						href="User_Controller?type=messagePage&userId=<%=userId%>"><i
							class="far fa-comments"></i></a></li>
					<li class="follow"><a
						href="User_Controller?type=followPage&userId=<%=userId%>"><i
							class="far fa-heart"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=profilePage&userId=<%=userId%>"><i
							class="far fa-user"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=logout&userId=<%=userId%>"><i
							class="fas fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page-change-password">
		<div class="left-main-page-change-password">
			<ul>
				<li><a
					href="User_Controller?type=editProfilePage&userId=<%=userId%>">Edit
						profile</a></li>
				<li><a
					href="User_Controller?type=changePasswordPage&userId=<%=userId%>">Change
						Password</a></li>
			</ul>
		</div>
		<div class="right-main-page-change-password">
			<form action="User_Controller?type=changePassword&userId=<%=userId%>"
				method="post">
				<table>
					<thead></thead>
					<tbody>
						<tr>
							<td><img
								src="https://scr.vn/wp-content/uploads/2020/11/avatar-instagram-trong.jpg"
								alt=" " width="50" height="50"></td>
							<td class="name-user"><strong><%=name%></strong></td>
						</tr>
						<tr>
							<td><strong>Old password</strong></td>
							<td><input type="password" name="oldpassword"></td>
						</tr>
						<tr>
							<td><strong>New password</strong></td>
							<td><input type="password" name="newpassword"></td>
						</tr>
						<tr>
							<td><strong>Confirm password</strong></td>
							<td><input type="password" name="confirmpassword"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Submit"
								class="submit-change-password"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="bottom-page">
		<div class="box-bottom">
			<div class="team-member">
				<ul>
					<li><a href="https://www.facebook.com/user001111000101/">Nguyen
							Dang Kiet</a></li>
					<li><a href="https://www.facebook.com/thanh2931">Duong
							Dinh Thanh</a></li>
					<li><a
						href="https://www.facebook.com/profile.php?id=100008118754465">Nguyen
							Tan Sy</a></li>
				</ul>
			</div>
			<div class="university">
				<ul>
					<li><a href="http://dut.udn.vn/">Da Nang University of
							Technology</a></li>
					<li><a href="http://dut.udn.vn/KhoaCNTT">IT Falcuty</a></li>
					<li><a href="#">PBL4</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>