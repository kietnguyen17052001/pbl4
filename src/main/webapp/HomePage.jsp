<%@page import="Model.BEAN.Following_Post"%>
<%@page import="java.util.*"%>
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
		int userId = (int) request.getAttribute("userId");
	List<User> listFollowing = (List<User>) request.getAttribute("listFollowing");
	ArrayList<Following_Post> listFollowingPost = (ArrayList<Following_Post>) request.getAttribute("listFollowingPost");
	User user = (User) request.getAttribute("user");
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
	<div class="main-page-home">
		<div class="container">
			<div class="left-home">
				<div class="following">
					<%
						for (User item : listFollowing) {
					%>
					<a class="frame-following" href="#">
						<div class="img-following">
							<img class="avatar-following" alt="Avatar"
								src="image/<%=item.getPhoto()%>"><br>
						</div> <strong class="name-following"><%=item.getFirst_name()%></strong>
					</a>
					<%
						}
					%>
				</div>
				<div class="post">
					<div class="frame-post">
						<%
							for (Following_Post item : listFollowingPost) {
						%>
						<div class="frame-one-post">
							<div class="top-post">
								<img class="avatar-post" alt="Avatar"
									src="image/<%=item.getAvatar()%>">
								<div class="name-date">
									<a class="name-post" href="#"><strong><%=item.getlastName() + " " + item.getfirstName()%></strong></a>
									<p class="date-post"><%=item.getcreateDate()%></p>
								</div>
							</div>
							<p class="content-post"><%=item.getContent()%></p>
							<img class="photo-post" alt="Post"
								src="image/<%=item.getPhoto()%>">
						</div>

						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="right-home">
				<div class="user-login">
					<img class="avatar-user" alt="Avatar"
						src="image/<%=user.getPhoto()%>">
					<div class="userId-name">
						<a class="name-user-home"
							href="User_Controller?type=profilePage&userId=<%=userId%>"><strong><%=user.getLast_name() + " " + user.getFirst_name()%></strong></a>
					</div>
				</div>
				<div class="hint">
					<div class="hint-header">
						<p class="text-hint">Suggestions for you</p>
						<a class="show-all-hint" href="#">Show all</a>
					</div>
					<div class = "hint-content">
						
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>