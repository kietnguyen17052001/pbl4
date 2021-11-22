<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.User"%>
<%@ page import="Model.BEAN.Post_Photo"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<script src="js/Event_With_Form.js"></script>
<title>Profile</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	int userId = user.getUser_id();
	HashMap<User, Boolean> hashMapListSearch = (HashMap<User, Boolean>) request.getAttribute("hashMapp");
	HashMap<User, String> hashMapListFollower = (HashMap<User, String>) request.getAttribute("hashMap");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage">Sugar App</a>
			</div>
			<div class="search">
				<form action="User_Controller?type=search" method="post">
					<input type="text" placeholder="Search" name="contentSearch">
					<input type="submit" value="Search">
				</form>
			</div>
			<div class="form-list-user"></div>
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
	<div class="main-page-search">
		<%
		String type;
		for (User another : hashMapListSearch.keySet()) {
			String name = another.getFull_name();
			// hashMap.get(user) == true: user is following target
			type = hashMapListSearch.get(another) ? "unfollow" : "follow";
		%>
		<form
			action="Follow_Controller?type=<%=type%>&pageFollow=profilePage&targetId=<%=another.getUser_id()%>"
			method="post">
			<div class="main-page-search-user">
				<div class="main-page-search-user-avatar">
					<a
						href="User_Controller?type=anotherProfilePage&anotherUserId=<%=another.getUser_id()%>"><img
						src="image/<%=another.getPhoto()%>" alt="avatar" width="70"
						height="70"></a>
				</div>
				<div class="main-page-search-user-information">
					<ul>
						<li><a
							href="User_Controller?type=anotherProfilePage&anotherUserId=<%=another.getUser_id()%>"><strong><%=name%></strong></a></li>
					</ul>
				</div>
				<div class="main-page-search-user-<%=type%>">
					<input type="submit" value=<%=type%>>
				</div>
			</div>
		</form>
		<%
		}
		%>
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
		<%
		for (User userFollower : hashMapListFollower.keySet()) {
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
					<strong><%=nameUserFollower%></strong> started following you. <%=hashMapListFollower.get(userFollower)%>
				</a>
			</div>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>