<%@page import="java.text.SimpleDateFormat"%>
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
	String name = user.getFull_name();
	List<Post_Photo> listPost = (ArrayList<Post_Photo>) request.getAttribute("listPost");
	List<User> listFollowing = (ArrayList<User>) request.getAttribute("listFollowing");
	List<User> listFollower = (ArrayList<User>) request.getAttribute("listFollower");
	HashMap<User, String> hashMap = (HashMap<User, String>) request.getAttribute("hashMap");
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
								<form action="User_Controller?type=editProfilePage"
									method="post">
									<input class="edit-profile" type="submit" value="Edit profile">
								</form>
							</td>
							<td><button type="button" class="up-post"
									onclick="openFormPostPhoto()">New post</button></td>
						</tr>
						<tr>
							<td class="num-post"><strong><%=user.getPost()%></strong>
								post</td>
							<td><button class="btn-show-follower"
									onclick="openFormListFollower()">
									<strong><%=user.getFollower()%></strong> followers
								</button></td>
							<td><button class="btn-show-following"
									onclick="openFormListFollowing()">
									Following <strong><%=user.getFollowing()%></strong> users
								</button></td>
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
			<div class="list-post-in-profile">
				<%
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String formatDate;
				for (int i = listPost.size() - 1; i >= 0; i--) {
					formatDate = sdf.format(listPost.get(i).getCreate_date());
				%>
				<div
					onclick="openFormPostPhotoContent('user','<%=userId%>','<%=listPost.get(i).getPost_id()%>','<%=listPost.get(i).getContent()%>','<%=listPost.get(i).getPhoto()%>','<%=formatDate%>')">
					<img src="image/<%=listPost.get(i).getPhoto()%>" alt="image-post">
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
	<div id="form-post" class="post-photo">
		<div class="post-photo-title">
			<div>
				<button type="button">
					<i class="far fa-question-circle"></i>
				</button>
			</div>
			<div>
				<p>Create new post</p>
			</div>
			<div>
				<button type="button">
					<i class="far fa-times-circle" onclick="closeFormPostPhoto()"></i>
				</button>
			</div>
		</div>
		<form action="Post_Photo_Controller?type=add" method="post"
			enctype="multipart/form-data">
			<div class="post-photo-box">
				<div class="post-photo-main">
					<div class="post-photo-user">
						<img src="image/<%=user.getPhoto()%>" alt="avatar" width="50"
							height="50">
						<p>
							<strong><%=name%></strong>
						</p>
					</div>
					<div class="post-photo-content-textarea">
						<textarea name="photo-content" id="" cols="30" rows="5"
							placeholder="Write your content ..."></textarea>
					</div>
				</div>
				<div class="post-photo-image">
					<input type="file" name="image">
				</div>
				<div class="post-photo-submit">
					<input type="submit" value="Up post">
				</div>
			</div>
		</form>
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
		<div>
			<%
			for (User userFollowing : listFollowing) {
				String nameUserFollowing = userFollowing.getLast_name() + " " + userFollowing.getFirst_name();
			%>
			<form
				action="Follow_Controller?type=unfollow&pageFollow=profilePage&targetId=<%=userFollowing.getUser_id()%>"
				method="post">
				<div class="list-following">
					<div class="list-following-user-avatar">
						<a
							href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollowing.getUser_id()%>"><img
							src="image/<%=userFollowing.getPhoto()%>" alt="avatar"
							height="50" width="50"></a>
					</div>
					<div class="list-following-user-name">
						<a
							href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollowing.getUser_id()%>"><%=nameUserFollowing%></a>
					</div>
					<div class="list-following-user-unfollow">
						<input type="submit" value="Unfollow">
					</div>
				</div>
			</form>
			<%
			}
			%>
		</div>
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
		<div>
			<%
			for (User userFollower : listFollower) {
				String nameUserFollower = userFollower.getLast_name() + " " + userFollower.getFirst_name();
			%>
			<form
				action="Follow_Controller?type=delete&pageFollow=profilePage&targetId=<%=userFollower.getUser_id()%>"
				method="post">
				<div class="list-follower">
					<div class="list-follower-user-avatar">
						<a
							href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>"><img
							src="image/<%=userFollower.getPhoto()%>" alt="avatar" height="50"
							width="50"></a>
					</div>
					<div class="list-follower-user-name">
						<a
							href="User_Controller?type=anotherProfilePage&anotherUserId=<%=userFollower.getUser_id()%>"><%=nameUserFollower%></a>
					</div>
					<div class="list-follower-user-delete">
						<input type="submit" value="Delete">
					</div>
				</div>
			</form>
			<%
			}
			%>
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
		<div>
			<%
			for (User userFollower : hashMap.keySet()) {
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
						<strong><%=userFollower.getFull_name()%></strong> started
						following you
					</a>
				</div>
				<div>
					<%=hashMap.get(userFollower)%>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<div id="form-post-photo-content"
		class="post-photo-content-profile-page">
		<div class="post-photo-content-title">
			<div>
				<button type="button">
					<i class="far fa-question-circle"></i>
				</button>
			</div>
			Post
			<div>
				<button type="button">
					<i class="far fa-times-circle"
						onclick="closeFormPostPhotoContent()"></i>
				</button>
			</div>
		</div>
		<div class="box-post-photo-content">
			<div class="post-photo-content-user">
				<div class="avatar">
					<a href="User_Controller?type=profilePage"> <img
						src="image/<%=user.getPhoto()%>" alt="avatar-user" width="70"
						height="70">
					</a>
				</div>
				<div class="name-user">
					<a href="User_Controller?type=profilePage"><strong><%=name%></strong></a>
					<p id="create-date"></p>
				</div>
				<div class="submit-edit-delete">
					<form id="submit-edit-post" action="" method="post">
						<input type="submit" value="Edit post">
					</form>
					<form id="submit-delete-post" action="" method="post">
						<input type="submit" value="Delete post">
					</form>
				</div>
			</div>
			<div id="post-photo-content-content"
				class="post-photo-content-content"></div>
			<div class="post-photo-content-photo">
				<img id="photo" src="" alt="image-post">
			</div>
		</div>
	</div>
</body>
</html>