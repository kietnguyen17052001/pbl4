<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Profile</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	int userId = user.getUser_id();
	User anotherUser = (User) request.getAttribute("anotherUser");
	boolean isFollowing = (boolean) request.getAttribute("isFollowing");
	boolean isFollowed = (boolean) request.getAttribute("isFollowed");
	List<Post_Photo> listPost = (ArrayList<Post_Photo>) request.getAttribute("listPost");
	HashMap<User, Integer> hashMapListFollowing = (HashMap<User, Integer>) request.getAttribute("hashMapListFollowing");
	HashMap<User, Integer> hashMapListFollower = (HashMap<User, Integer>) request.getAttribute("hashMapListFollower");
	String followingOfAnother = (String) request.getAttribute("followingOfAnother");
	LinkedHashMap<User, String> hashMapNotification = (LinkedHashMap<User, String>) request.getAttribute("linkedHashMap");
	int newFollower = (int) request.getAttribute("newFollower");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage">Sugar App</a>
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
	<div class="main-page">
		<div class="box-main">
			<div class="user">
				<div class="left">
					<img class="avatar" alt="Avatar"
						src="image/<%=anotherUser.getPhoto()%>">
					<%
					if (isFollowing && isFollowed && anotherUser.getUser_status().equals("online")) { // follow each other and another's status is online
					%>
					<div class="status"></div>
					<%
					}
					%>
				</div>
				<div class="right">
					<table>
						<tr>
							<td class="name-user"><%=anotherUser.getFull_name()%></td>
							<td>
								<%
								if (isFollowing) { // user is following another
								%>
								<form
									action="User_Controller?type=chatWithUser&targetId=<%=anotherUser.getUser_id()%>"
									method="post">
									<input class="message-another" type="submit" value="Message">
								</form> <%
 } else {
 %>
								<form
									action="Follow_Controller?type=follow&pageFollow=anotherProfilePage&targetId=<%=anotherUser.getUser_id()%>&anotherUserId=<%=anotherUser.getUser_id()%>"
									method="post">
									<input class="follow-another" type="submit" value="Follow">
								</form> <%
 }
 %>
							</td>
							<td>
								<%
								if (isFollowing) {
								%>
								<form
									action="Follow_Controller?type=unfollow&pageFollow=anotherProfilePage&targetId=<%=anotherUser.getUser_id()%>&anotherUserId=<%=anotherUser.getUser_id()%>"
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
						<%
						if (followingOfAnother != "") {
						%>
						<p>
							<%=followingOfAnother%>
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
					onclick="openFormPostPhotoContent('another','<%=userId%>','<%=listPost.get(i).getPost_id()%>','<%=listPost.get(i).getContent()%>','<%=listPost.get(i).getPhoto()%>','<%=formatDate%>')">
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
			String typeUserFollowing;
			for (User userFollowing : hashMapListFollowing.keySet()) {
				// hashMap.get(user) == 1: user is following target
				typeUserFollowing = (hashMapListFollowing.get(userFollowing) == 1) ? "unfollow" : "follow";
			%>
			<form
				action="Follow_Controller?type=<%=typeUserFollowing%>&pageFollow=anotherProfilePage&targetId=<%=userFollowing.getUser_id()%>&anotherUserId=<%=anotherUser.getUser_id()%>"
				method="post">
				<div class="list-following">
					<%
					boolean isUser = (userFollowing.getUser_id() == userId) ? true : false;
					String href = (isUser) ? "User_Controller?type=profilePage"
							: "User_Controller?type=anotherProfilePage&anotherUserId=" + userFollowing.getUser_id();
					%>
					<div class="list-following-user-avatar">
						<a href="<%=href%>"><img
							src="image/<%=userFollowing.getPhoto()%>" alt="avatar"
							height="50" width="50"></a>
					</div>
					<div class="list-following-user-name">
						<a href="<%=href%>"><%=userFollowing.getFull_name()%></a>
					</div>
					<%
					if (!isUser) {
					%>
					<div class="list-following-user-<%=typeUserFollowing%>">
						<input type="submit" value="<%=typeUserFollowing%>">
					</div>
					<%
					}
					%>
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
			String typeUserFollower;
			for (User userFollower : hashMapListFollower.keySet()) {
				String nameUserFollower = userFollower.getLast_name() + " " + userFollower.getFirst_name();
				typeUserFollower = (hashMapListFollower.get(userFollower) == 1) ? "unfollow" : "follow";
			%>
			<form
				action="Follow_Controller?type=<%=typeUserFollower%>&pageFollow=anotherProfilePage&targetId=<%=userFollower.getUser_id()%>&anotherUserId=<%=anotherUser.getUser_id()%>"
				method="post">
				<div class="list-follower">
					<%
					boolean isUser = (userFollower.getUser_id() == userId) ? true : false;
					String href = (isUser) ? "User_Controller?type=profilePage"
							: "User_Controller?type=anotherProfilePage&anotherUserId=" + userFollower.getUser_id();
					%>
					<div class="list-follower-user-avatar">
						<a href="<%=href%>"><img
							src="image/<%=userFollower.getPhoto()%>" alt="avatar" height="50"
							width="50"></a>
					</div>
					<div class="list-follower-user-name">
						<a href="<%=href%>"><%=nameUserFollower%></a>
					</div>
					<%
					if (!isUser) {
					%>
					<div class="list-follower-user-<%=typeUserFollower%>">
						<input type="submit" value="<%=typeUserFollower%>">
					</div>
					<%
					}
					%>
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
					<a
						href="User_Controller?type=anotherProfilePage&anotherUserId=<%=anotherUser.getUser_id()%>">
						<img src="image/<%=anotherUser.getPhoto()%>" alt="avatar-user"
						width="70" height="70">
					</a>
				</div>
				<div class="name-user">
					<a
						href="User_Controller?type=anotherProfilePage&anotherUserId=<%=anotherUser.getUser_id()%>"><strong><%=anotherUser.getFull_name()%></strong></a>
					<p id="create-date"></p>
				</div>
			</div>
			<div id="post-photo-content-content"
				class="post-photo-content-content"></div>
			<div class="post-photo-content-photo">
				<img id="photo" src="" alt="image-post">
			</div>
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