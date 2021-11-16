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
	User anotherUser = (User) request.getAttribute("anotherUser");
	int userId = (int) request.getAttribute("userId");
	boolean isFollowed = (boolean) request.getAttribute("isFollowed");
	String nameAnotherUser = anotherUser.getLast_name() + " " + anotherUser.getFirst_name();
	List<Post_Photo> listPost = (ArrayList<Post_Photo>) request.getAttribute("listPost");
	List<User> listFollowing = (ArrayList<User>) request.getAttribute("listFollowing");
	List<User> listFollower = (ArrayList<User>) request.getAttribute("listFollower");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage&userId=<%=userId%>">Sugar
					App</a>
			</div>
			<div class="search">
				<form action="User_Controller?type=search&userId=<%=userId%>"
					method="post">
					<input type="text" placeholder="Search" name="contentSearch">
					<input type="submit" value="Search">
				</form>
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
					<img class="avatar" alt="Avatar"
						src="image/<%=anotherUser.getPhoto()%>">
				</div>
				<div class="right">
					<table>
						<tr>
							<td class="name-user"><%=nameAnotherUser%></td>
							<td>
								<%
								if (isFollowed) {
								%>
								<form
									action="User_Controller?type=messagePage&userId=<%=userId%>"
									method="post">
									<input class="message-another" type="submit" value="Message">
								</form> <%
 } else {
 %>
								<form
									action="Follow_Controller?type=follow&pageFollow=anotherProfilePage&userId=<%=userId%>&targetId=<%=anotherUser.getUser_id()%>&anotherUserId=<%=anotherUser.getUser_id()%>"
									method="post">
									<input class="follow-another" type="submit" value="Follow">
								</form> <%
 }
 %>
							</td>
							<td>
								<%
								if (isFollowed) {
								%>
								<form
									action="Follow_Controller?type=unfollow&pageFollow=anotherProfilePage&userId=<%=userId%>&targetId=<%=anotherUser.getUser_id()%>&anotherUserId=<%=anotherUser.getUser_id()%>"
									method="post">
									<input class="unfollow-another" type="submit" value="Unfollow">
								</form> <%
 }
 %>
							</td>
						</tr>
						<tr>
							<td class="num-post"><strong><%=anotherUser.getPost()%></strong>
								post</td>
							<td><button class="btn-show-follower"
									onclick="openFormListFollower()">
									<strong><%=anotherUser.getFollower()%></strong> followers
								</button></td>
							<td><button class="btn-show-following"
									onclick="openFormListFollowing()">
									Following <strong><%=anotherUser.getFollowing()%></strong>
									users
								</button></td>
						</tr>
					</table>
					<div class="about-user">
						<%
						if (!anotherUser.getAbout().equals("")) {
						%>
						<p>
							<strong>About me: </strong><%=anotherUser.getAbout()%></p>
						<%
						}
						%>
						<%
						if (!anotherUser.getFacebook().equals("")) {
						%>
						<p>
							<strong>Facebook: </strong><a
								href="<%=anotherUser.getFacebook()%>"><%=anotherUser.getFacebook()%></a>
						</p>
						<%
						}
						%>
						<%
						if (!anotherUser.getInstagram().equals("")) {
						%>
						<p>
							<strong>Instagram: </strong><a
								href="<%=anotherUser.getInstagram()%>"><%=anotherUser.getInstagram()%></a>
						</p>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<div class="list-post-in-profile">
				<%
				for (int i = listPost.size() - 1; i >= 0; i--) {
				%>
				<div>
					<a href="#"><img src="image/<%=listPost.get(i).getPhoto()%>"
						alt="image-post"></a>
				</div>
				<%
				}
				%>
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
	<div id="form-following" class="form-list-following">
		<div class="form-list-following-title">
			<div>
				<button type="button">
					<i class="far fa-question-circle"></i>
				</button>
			</div>
			<div>
				<p>Following</p>
			</div>
			<div>
				<button type="button">
					<i class="far fa-times-circle" onclick="closeFormListFollowing()"></i>
				</button>
			</div>
		</div>
		<%
		for (User userFollowing : listFollowing) {
			String nameUserFollowing = userFollowing.getLast_name() + " " + userFollowing.getFirst_name();
		%>
		<form action="" method="post">
			<div class="list-following">
				<div class="list-following-user-avatar">
					<a href=""><img src="image/<%=userFollowing.getPhoto()%>"
						alt="avatar" height="50" width="50"></a>
				</div>
				<div class="list-following-user-name"><%=nameUserFollowing%></div>
				<div class="list-following-user-unfollow">
					<input type="submit" value="Unfollow">
				</div>
			</div>
		</form>
		<%
		}
		%>
	</div>
	<div id="form-follower" class="form-list-follower">
		<div class="form-list-follower-title">
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
					<i class="far fa-times-circle" onclick="closeFormListFollower()"></i>
				</button>
			</div>
		</div>
		<%
		for (User userFollower : listFollower) {
			String nameUserFollower = userFollower.getLast_name() + " " + userFollower.getFirst_name();
		%>
		<form action="" method="post">
			<div class="list-follower">
				<div class="list-follower-user-avatar">
					<a href=""><img src="image/<%=userFollower.getPhoto()%>"
						alt="avatar" height="50" width="50"></a>
				</div>
				<div class="list-follower-user-name"><%=nameUserFollower%></div>
				<div class="list-follower-user-delete">
					<input type="submit" value="Delete">
				</div>
			</div>
		</form>
		<%
		}
		%>
	</div>
</body>
</html>