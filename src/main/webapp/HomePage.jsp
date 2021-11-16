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
	int userId = (int) request.getAttribute("userId");
	%>
	<div class="top-page">
		<div class="box-top">
			<div class="name-app">
				<a href="User_Controller?type=homePage&userId=<%=userId%>"
					target="top-main">Suger App</a>
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
					<li class="logout"><a
						href="User_Controller?type=logout&userId=<%=userId%>"><i
							class="fas fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-page-home"></div>
</body>
</html>