<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<script src="js/Event_With_Form.js"></script>
<script src="js/Exception.js"></script>
<title>Sugar App</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	User anotherUser = null;
	List<User> listUserFollowing = (List<User>) request.getAttribute("listUserFollowing");
	List<User> listTopExplore = (List<User>) request.getAttribute("listTopExplore");
	HashMap<Post_Photo, User> hashMapPostPhoto_User = (HashMap<Post_Photo, User>) request
			.getAttribute("hashMapPostPhoto_User");
	HashMap<User, String> hashMapNotification = (HashMap<User, String>) request.getAttribute("hashMap");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage" target="top-main">Suger
					App</a>
			</div>
			<div class="search">
				<form action="User_Controller?type=search" method="post">
					<input type="text" placeholder="Search" name="contentSearch">
					<input id="search-user" type="submit" value="Search">
				</form>
			</div>
			<div class="option">
				<ul>
					<li class="home"><a href="User_Controller?type=homePage"><i
							class="fas fa-home"></i></a></li>
					<li class="message"><a href="User_Controller?type=messagePage"><i
							class="far fa-comments"></i></a></li>
					<li class="follow" onclick="openFormListFollowerHistory()"><a><i
							class="far fa-heart"></i></a></li>
					<li class="profile"><a href="User_Controller?type=profilePage"><i
							class="far fa-user"></i></a></li>
					<li class="profile"><a href="User_Controller?type=logout"><i
							class="fas fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page">
		<div class="box-main-home-page">
			<div class="box-main-home-page-left">
				<div class="sub-box-main-home-page-left">
					<div class="list-user-following">
						<div>
							<ul>
								<%
								for (User userFollowing : listUserFollowing) {
								%>
								<li><a
									href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollowing.getUser_id()%>"><img
										src="image/<%=userFollowing.getPhoto()%>" alt="avatar"
										width="60" height="60"></a></li>
								<%
								}
								%>
							</ul>
						</div>
					</div>
					<div class="list-post-photo-user-following">
						<%
						for (HashMap.Entry<Post_Photo, User> entry : hashMapPostPhoto_User.entrySet()) {
							Post_Photo key = entry.getKey(); // post user following
							User value = entry.getValue(); // user following
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String createDate = sdf.format(key.getCreate_date());
						%>
						<div class="box-post-photo-content">
							<div class="post-photo-content-user">
								<div class="avatar">
									<a
										href="User_Controller?type=anotherProfilePage&anotherUserId=<%=value.getUser_id()%>">
										<img src="image/<%=value.getPhoto()%>" alt="avatar-user"
										width="45" height="45">
									</a>
								</div>
								<div class="name-user">
									<a
										href="User_Controller?type=anotherProfilePage&anotherUserId=<%=value.getUser_id()%>"><strong><%=value.getLast_name() + " " + value.getFirst_name()%></strong></a>
									<p id="create-date"><%=createDate%></p>
								</div>
							</div>
							<div id="post-photo-content-content"
								class="post-photo-content-content"><%=key.getContent()%></div>
							<div class="post-photo-content-photo">
								<img id="photo" src="image/<%=key.getPhoto()%>" alt="image-post">
							</div>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<div class="box-main-home-page-right">
				<div class="box-fixed">
					<div class="box-main-home-page-right-user">
						<div class="avatar-user">
							<a href="User_Controller?type=profilePage"><img
								src="image/<%=user.getPhoto()%>" alt="avatar" width="50"
								height="50"></a>
						</div>
						<div class="name-user">
							<a href="User_Controller?type=profilePage"><%=user.getLast_name() + " " + user.getFirst_name()%></a>
						</div>
					</div>
					<div class="box-main-home-page-right-explore">
						<div class="suggestions">
							<div>Suggestions</div>
							<div>
								<a href="User_Controller?type=explorePeople">See all</a>
							</div>
						</div>
						<div class="list-suggestions">
							<%
							for (User userExplore : listTopExplore) {
							%>
							<div class="another">
								<div class="avatar">
									<a
										href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userExplore.getUser_id()%>"><img
										src="image/<%=userExplore.getPhoto()%>" alt="avatar"
										width="35" height="35"></a>
								</div>
								<div class="name-user">
									<a
										href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userExplore.getUser_id()%>"><%=userExplore.getLast_name() + " " + userExplore.getFirst_name()%></a>
								</div>
								<div class="follow">
									<a
										href="Follow_Controller?type=follow&pageFollow=homePage&targetId=<%=userExplore.getUser_id()%>">Follow</a>
								</div>
							</div>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="form-follow-history" class="form-list-follower-history">
		<div class="form-list-follower-history-title">
			<div>
				<button type="button">
					<i class="far fa-question-circle"></i>
				</button>
			</div>
			<div>
				<p>Followers</p>
			</div>
			<div>
				<button type="button">
					<i class="far fa-times-circle"
						onclick="closeFormListFollowerHistory()"></i>
				</button>
			</div>
		</div>
		<%
		for (User userFollower : hashMapNotification.keySet()) {
			String nameUserFollower = userFollower.getLast_name() + " " + userFollower.getFirst_name();
		%>
		<div class="list-follower">
			<div class="list-follower-user-avatar">
				<a
					href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>"><img
					src="image/<%=userFollower.getPhoto()%>" alt="avatar" height="50"
					width="50"></a>
			</div>
			<div class="list-follower-history-user-name">
				<a
					href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>">
					<strong><%=nameUserFollower%></strong> started following you. <%=hashMapNotification.get(userFollower)%>
				</a>
			</div>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>