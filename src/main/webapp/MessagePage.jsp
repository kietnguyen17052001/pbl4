<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<script src="js/Event_With_Form.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Message</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	int userId = user.getUser_id();
	LinkedHashMap<User, String> hashMapNotification = (LinkedHashMap<User, String>) request.getAttribute("linkedHashMap");
	int newFollower = (int) request.getAttribute("newFollower");
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
					<input type="submit" value="Search">
				</form>
			</div>
			<div class="option">
				<ul>
					<li class="home"><a href="User_Controller?type=homePage"><i
							class="fas fa-home"></i></a></li>
					<li class="message"><a href="User_Controller?type=messagePage"><i
							class="far fa-comments"></i></a></li>
					<li class="follow" onclick="openFormListFollowerHistory()"><a><i
							class="far fa-heart"></i></a> <%
 if (newFollower > 0) {
 %>
						<div id="id-newFollowerNumber" class="newFollower"><%=newFollower%></div>
						<%
						}
						%></li>
					<li class="profile"><a href="User_Controller?type=profilePage"><i
							class="far fa-user"></i></a></li>
					<li class="profile"><a href="User_Controller?type=logout"><i
							class="fas fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page"></div>
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
		<div id="list-follower-history">
			<%
			for (User userFollower : hashMapNotification.keySet()) {
			%>
			<div class="list-follower">
				<div class="list-follower-user-avatar">
					<a
						href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>"><img
						src="image/<%=userFollower.getPhoto()%>" alt="avatar" height="50"
						width="50"></a>
				</div>
				<div class="list-follower-history-user-name">
					<%
					if (newFollower > 0) {
					%>
					<a
						href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>"
						style="color: #eb4d4b"> <strong><%=userFollower.getFull_name()%></strong>
						started following you
					</a>
					<%
					newFollower--;
					} else {
					%>
					<a
						href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>">
						<strong><%=userFollower.getFull_name()%></strong> started
						following you
					</a>
					<%
					}
					%>
				</div>
				<div>
					<%=hashMapNotification.get(userFollower)%>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>