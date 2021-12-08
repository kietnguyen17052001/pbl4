<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.BEAN.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/Page.css" type="text/css">
<script type="text/javascript" src="js/Event_With_Form.js"></script>
<script type="text/javascript" src="js/Login_Registration.js"></script>
<script type="text/javascript" src="js/Exception.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Sugar App</title>
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
				<a href="User_Controller?type=homePage" target="top-main">Sugar
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
	<div class="main-page-change-password">
		<div class="left-main-page-change-password">
			<ul>
				<li><a href="User_Controller?type=editProfilePage">Edit
						profile</a></li>
				<li><a href="User_Controller?type=changePasswordPage">Change
						Password</a></li>
			</ul>
		</div>
		<div class="right-main-page-change-password">
			<form name="formChangePassword"
				action="User_Controller?type=changePassword&subType=changePasswordPage"
				method="post">
				<table>
					<thead></thead>
					<tbody>
						<tr>
							<td><img src="image/<%=user.getPhoto()%>" alt=" " width="50"
								height="50"></td>
							<td class="name-user"><strong><%=user.getFull_name()%></strong></td>
						</tr>
						<tr>
							<td><strong>Old password</strong></td>
							<td><input id="input-oldpassword" type="password"
								name="oldpassword"><i id="eye-oldpassword"
								class="fas fa-eye-slash" onclick="togglePassword('oldPassword')"></i></td>
						</tr>
						<tr>
							<td><strong>New password</strong></td>
							<td><input id="input-newpassword" type="password"
								name="newpassword"><i id="eye-newpassword"
								class="fas fa-eye-slash" onclick="togglePassword('newPassword')"></i></td>
						</tr>
						<tr>
							<td><strong>Confirm password</strong></td>
							<td><input id="input-confirmpassword" type="password"
								name="confirmpassword"><i id="eye-confirmpassword"
								class="fas fa-eye-slash"
								onclick="togglePassword('confirmPassword')"></i></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Submit"
								class="submit-change-password"
								onclick="return checkChangePassword()"></td>
						</tr>
					</tbody>
				</table>
			</form>
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