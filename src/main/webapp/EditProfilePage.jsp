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
<script src="js/Event_With_Form.js"></script>
<title>Sugar App</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	int userId = user.getUser_id();
	String lastName = user.getLast_name();
	String firstName = user.getFirst_name();
	String city = user.getCity();
	String photo = user.getPhoto();
	String aboutMe = user.getAbout();
	String job = user.getJob();
	String company = user.getCompany();
	String facebook = user.getFacebook();
	String instagram = user.getInstagram();
	String email = user.getEmail();
	String phoneNumber = user.getPhone();
	int gender = user.getGender();
	HashMap<User, String> hashMap = (HashMap<User, String>) request.getAttribute("hashMap");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage"
					target="top-main">Sugar App</a>
			</div>
			<div class="search">
				<form action="User_Controller?type=search"
					method="post">
					<input type="text" placeholder="Search" name="contentSearch">
					<input type="submit" value="Search">
				</form>
			</div>
			<div class="option">
				<ul>
					<li class="home"><a
						href="User_Controller?type=homePage"><i
							class="fas fa-home"></i></a></li>
					<li class="message"><a
						href="User_Controller?type=messagePage"><i
							class="far fa-comments"></i></a></li>
					<li class="follow" onclick="openFormListFollowerHistory()"><a><i
							class="far fa-heart"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=profilePage"><i
							class="far fa-user"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=logout"><i
							class="fas fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page-edit-profile">
		<div class="left-main-page-edit-profile">
			<ul>
				<li><a
					href="User_Controller?type=editProfilePage">Edit
						profile</a></li>
				<li><a
					href="User_Controller?type=changePasswordPage">Change
						Password</a></li>
			</ul>
		</div>
		<div class="right-main-page-edit-profile">
			<form action="User_Controller?type=edit"
				method="post" enctype="multipart/form-data">
				<table>
					<thead></thead>
					<tbody>
						<tr>
							<td><img src="image/<%=photo%>" alt="avatar" width="50"
								height="50"></td>
							<td>
								<ul>
									<li><strong>Change avatar</strong></li>
									<li><input type="file" name="image" class="select-image"></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td><strong>Last name</strong></td>
							<td><input type="text" value="<%=lastName%>" name="lastname"></td>
						</tr>
						<tr>
							<td><strong>First name</strong></td>
							<td><input type="text" value="<%=firstName%>"
								name="firstname"></td>
						</tr>
						<tr>
							<td><strong>City</strong></td>
							<td><input type="text" value="<%=city%>" name="city"></td>
						</tr>
						<tr>
							<td><strong>About me</strong></td>
							<td><textarea name="about" cols="50" rows="3"><%=aboutMe%></textarea></td>
						</tr>
						<tr>
							<td><strong>Job</strong></td>
							<td><input type="text" value="<%=job%>" name="job"></td>
						</tr>
						<tr>
							<td><strong>Company</strong></td>
							<td><input type="text" value="<%=company%>" name="company"></td>
						</tr>
						<tr>
							<td><strong>Link Facebook</strong></td>
							<td><input type="text" value="<%=facebook%>" name="facebook"></td>
						</tr>
						<tr>
							<td><strong>Link Instagram</strong></td>
							<td><input type="text" value="<%=instagram%>"
								name="instagram"></td>
						</tr>
						<tr>
							<td><strong>Gender</strong></td>
							<td><select name="gender" class="gender">
									<option value="1">Male</option>
									<option value="0">Female</option>
									<option value="3">Other</option>
							</select></td>
						</tr>
						<tr>
							<td><strong>Email</strong></td>
							<td><input type="email" value="<%=email%>" name="email"></td>
						</tr>
						<tr>
							<td><strong>Phone number</strong></td>
							<td><input type="text" value="<%=phoneNumber%>" name="phone"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Submit" class="submit-edit"></td>
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
					<strong><%=userFollower.getFull_name()%></strong> started following you. <%=hashMap.get(userFollower)%>
				</a>
			</div>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>