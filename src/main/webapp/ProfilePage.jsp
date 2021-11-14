<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<title>Profile</title>
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
				<a href="User_Controller?type=homePage&userId=<%=userId%>">Suger
					App</a>
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
	<div class="main-page">
		<div class="box-main">
			<div class="user">
				<div class="left">
					<img class="avatar" alt="Avatar" src="image/<%=user.getPhoto()%>">
				</div>
				<div class="right">
					<table>
						<tr>
							<td class="name-user"><%=name%></td>
							<td>
								<form
									action="User_Controller?type=editProfilePage&userId=<%=userId%>"
									method="post">
									<input class="edit-profile" type="submit" value="Edit profile">
								</form>
							</td>
							<td>
								<form action="" method="post">
									<input class="up-post" type="submit" value="New post">
								</form>
							</td>
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
					<div class="about-user">
						<%
						if (!user.getAbout().equals("")) {
						%>
						<p>
							<strong>About me: </strong><%=user.getAbout()%></p>
						<%
						}
						%>
						<%
						if (!user.getFacebook().equals("")) {
						%>
						<p>
							<strong>Facebook: </strong><a href="<%=user.getFacebook()%>"><%=user.getFacebook()%></a>
						</p>
						<%
						}
						%>
						<%
						if (!user.getInstagram().equals("")) {
						%>
						<p>
							<strong>Instagram: </strong><a href="<%=user.getInstagram()%>"><%=user.getInstagram()%></a>
						</p>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<div class="post-user"></div>
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