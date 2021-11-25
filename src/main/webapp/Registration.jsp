<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/89a4fa0ef7.js"
	crossorigin="anonymous"></script>
<title>Register</title>
<link rel="stylesheet" href="css/Registration.css" type="text/css" />
<script type="text/javascript" src="js/Login_Registration.js"></script>
<script type="text/javascript" src="js/Exception.js"></script>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="top-form">
				<p class="name-app">SUGAR APP</p>
				<div class="form-signup">
					<form action="User_Controller?type=create" method="post"
						name="formRegistration">
						<input id="lastname" type="text" name="lastname"
							placeholder="Last name"> <input id="firstname"
							type="text" name="firstname" placeholder="First name"> <input
							id="email" type="email" name="email" placeholder="Email">
						<input id="phone" type="tel" name="phone"
							placeholder="Phone number" pattern=[0-9]{10}> <input
							id="username" type="text" name="username" placeholder="Username">
						<input id="input-password" type="password" name="password"
							placeholder="Password"> <i id="eye"
							class="fas fa-eye-slash" onclick="togglePassword('password')"></i>
						<div class="birthday">
							<p>Birthday</p>
							<div>
								<select name="day" id="">
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
								</select> <select name="month" id="">
									<option value="01">January</option>
									<option value="02">February</option>
									<option value="03">March</option>
									<option value="04">April</option>
									<option value="05">May</option>
									<option value="06">June</option>
									<option value="07">July</option>
									<option value="08">August</option>
									<option value="09">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
								</select> <select name="year" id="">
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>
									<option value="2016">2016</option>
									<option value="2015">2015</option>
									<option value="2014">2014</option>
									<option value="2013">2013</option>
									<option value="2012">2012</option>
									<option value="2011">2011</option>
									<option value="2010">2010</option>
									<option value="2009">2009</option>
									<option value="2008">2008</option>
									<option value="2007">2007</option>
									<option value="2006">2006</option>
									<option value="2005">2005</option>
									<option value="2004">2004</option>
									<option value="2003">2003</option>
									<option value="2002">2002</option>
									<option value="2001">2001</option>
									<option value="2000">2000</option>
									<option value="1999">1999</option>
									<option value="1998">1998</option>
									<option value="1997">1997</option>
									<option value="1996">1996</option>
									<option value="1995">1995</option>
									<option value="1994">1994</option>
									<option value="1993">1993</option>
									<option value="1992">1992</option>
									<option value="1991">1991</option>
									<option value="1990">1990</option>
									<option value="1989">1989</option>
									<option value="1988">1988</option>
									<option value="1987">1987</option>
									<option value="1986">1986</option>
									<option value="1985">1985</option>
									<option value="1984">1984</option>
									<option value="1983">1983</option>
									<option value="1982">1982</option>
									<option value="1981">1981</option>
									<option value="1980">1980</option>
									<option value="1979">1979</option>
									<option value="1978">1978</option>
									<option value="1977">1977</option>
									<option value="1976">1976</option>
									<option value="1975">1975</option>
								</select>
							</div>
						</div>
						<div class="gender">
							<p>Gender</p>
							<div>
								<select name="gender">
									<option value="1">Male</option>
									<option value="0">Female</option>
									<option value="3">Other</option>
								</select>
							</div>
						</div>
						<input type="submit" value="Register"
							onclick="return checkRegistration()">
					</form>
				</div>
			</div>
			<div class="bottom-form">
				<p>
					Do you have an account? <a href="Login.jsp" class="login">Login</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>