<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<title>Profile</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("user");
	String userId = String.valueOf(user.getUser_id());
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="#" target="top-main">Suger App</a>
			</div>
			<div class="search">
				<input type="text" placeholder="Search">
			</div>
			<div class="option">
				<ul>
					<li class="home"><a
						href="User_Controller?type=home&userId=<%=userId%>"
						target="center-main"><i class="fas fa-home"></i></a></li>
					<li class="message"><a
						href="User_Controller?type=message&userId=<%=userId%>"
						target="center-main"><i class="far fa-comments"></i></a></li>
					<li class="follow"><a
						href="User_Controller?type=follow&userId=<%=userId%>"><i
							class="far fa-heart"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=profile&userId=<%=userId%>"
						target="center-main"><i class="far fa-user"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page">
		<div class="box-main">
			<div class="user">
				<div class="left">
					<img alt="facebook.com" src="avatar">
				</div>
				<div class="right">
					<table>
						<tr>
							<td>Name</td>
							<td>
								<form action="" method="post">
									<input class="edit-profile" type="submit" value="Edit profile">
								</form>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="num-post"><strong><%=user.getPost()%></strong>
								post</td>
							<td><a href="#" class="num-follower"><strong><%=user.getFollower()%></strong></a>
								followers</td>
							<td>Following <a href="#" class="num-following"><strong><%=user.getFollowing()%></strong></a>
								users
							</td>
						</tr>
					</table>
					<div class="about-user"></div>
				</div>
			</div>
			<div class="post-user"></div>
		</div>
	</div>
	<div class="bottom-page">
		<div class="box-bottom">
			<div class="team-member">
				<ul>
					<li><a href="#">Nguyen Dang Kiet</a></li>
					<li><a href="#">Duong Dinh Thanh</a></li>
					<li><a href="#">Nguyen Tan Sy</a></li>
				</ul>
			</div>
			<div class="university">
				<ul>
					<li><a href="#">Da Nang University of Technology</a></li>
					<li><a href="#">IT Falcuty</a></li>
					<li><a href="#">PBL4</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>