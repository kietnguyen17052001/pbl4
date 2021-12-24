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
	List<User> listFollowing = (ArrayList<User>) request.getAttribute("listFollowing");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage" target="top-main">Suger
					App</a>
			</div>
			<div class="search">
				<form action="User_Controller?type=search" method="post">
					<input oninput="searchByName(this)" type="text"
						placeholder="Search" name="contentSearch"> <input
						id="search-user" type="submit" value="Search">
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
	<div class="main-page-mess">
		<div class="container-mess">
			<div class="left-mess">
				<div class="left-mess-header">
					<img class="img-avatar-mess" alt="avatar"
						src="image/<%=user.getPhoto()%>">
					<h4 class="name-mess"><%=user.getFull_name()%></h4>
				</div>
				<div class="left-mess-content">
					<%
					for (User following : listFollowing) {
					%>
					<div id="chat-with-user" class="following-one"
						onclick="chatBox(<%=userId%>, <%=following.getUser_id()%>)">
						<img class="img-avatar-mess" alt="avatar"
							src="image/<%=following.getPhoto()%>">
						<p class="name-mess"><%=following.getFull_name()%></p>
						<%
						if (following.getUser_status().equals("online")){
						%>
						<div class="status"></div>
						<%
						}
						%>
					</div>
					<%
					}
					%>
				</div>
			</div>
			<div class="right-mess">
				<div id="right-mess-box"></div>
				<div class="input-chat">
					<input type="text" id="content-message"
						placeholder="Enter your message ..."> <input type="button"
						onclick="sendMessage()" value="Send">
				</div>
			</div>
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
	<div id="form-load-search" class="load-search">
		<div class="title">
			<div>
				<button type="button">
					<i class="far fa-question-circle"></i>
				</button>
			</div>
			<div>
				<p>Search</p>
			</div>
			<div>
				<button type="button">
					<i class="far fa-times-circle" onclick="closeFormLoadSearch()"></i>
				</button>
			</div>
		</div>
		<div id="load-content-search"></div>
	</div>
</body>
</html>