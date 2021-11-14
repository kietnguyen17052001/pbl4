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
	User user = (User) request.getAttribute("user");
	int userId = user.getUser_id();
	String lastName = user.getLast_name();
	String firstName = user.getFirst_name();
	String city = user.getCity();
	String aboutMe = user.getAbout();
	String job = user.getJob();
	String company = user.getCompany();
	String facebook = user.getFacebook();
	String instagram = user.getInstagram();
	String email = user.getEmail();
	String phoneNumber = user.getPhone();
	int gender = user.getGender();
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
						href="User_Controller?type=home&userId=<%=userId%>"><i
							class="fas fa-home"></i></a></li>
					<li class="message"><a
						href="User_Controller?type=message&userId=<%=userId%>"><i
							class="far fa-comments"></i></a></li>
					<li class="follow"><a
						href="User_Controller?type=follow&userId=<%=userId%>"><i
							class="far fa-heart"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=profile&userId=<%=userId%>"><i
							class="far fa-user"></i></a></li>
					<li class="profile"><a
						href="User_Controller?type=logout&userId=<%=userId%>"><i
							class="fas fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page-edit-profile">
		<div class="left-main-page-edit-profile">
			<ul>
				<li><a
					href="User_Controller?type=editProfile&userId=<%=userId%>">Edit
						profile</a></li>
				<li><a
					href="User_Controller?type=changePassword&userId=<%=userId%>">Change
						Password</a></li>
			</ul>
		</div>
		<div class="right-main-page-edit-profile">
			<form action="User_Controller?type=edit&userId=<%=userId%>"
				method="post">
				<table>
					<thead></thead>
					<tbody>
						<tr>
							<td><img
								src="https://scr.vn/wp-content/uploads/2020/11/avatar-instagram-trong.jpg "
								alt=" " width="50 " height="50 "></td>
							<td class="change-avatar "><a href="#"><strong>Change
										avatar</strong></a></td>
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
</body>
</html>