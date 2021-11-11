<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản Sugar App</title>
<link rel="stylesheet" href="css/Registration.css" type="text/css" />
<script src="js/Login_Registration.js"></script>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="top-form">
				<p class="name-app">SUGAR APP</p>
				<div class="form-signup">
					<form action="User_Controller?type=create" method="post"
						name="registration">
						<input type="text" name="lastname" placeholder="Họ"> <input
							type="text" name="firstname" placeholder="Tên"> <input
							type="text" name="email" placeholder="Email"> <input
							type="text" name="phone" placeholder="Số điện thoại"> <input
							type="text" name="username" placeholder="Tên người dùng">
						<input type="password" name="password" placeholder="Mật khẩu">
						<div class="birthday">
							<p>Sinh nhật</p>
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
									<option value="01">Tháng 1</option>
									<option value="02">Tháng 2</option>
									<option value="03">Tháng 3</option>
									<option value="04">Tháng 4</option>
									<option value="05">Tháng 5</option>
									<option value="06">Tháng 6</option>
									<option value="07">Tháng 7</option>
									<option value="08">Tháng 8</option>
									<option value="09">Tháng 9</option>
									<option value="10">Tháng 10</option>
									<option value="11">Tháng 11</option>
									<option value="12">Tháng 12</option>
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
							<p>Giới tính</p>
							<div>
								<select name="gender">
									<option value="1">Nam</option>
									<option value="0">Nữ</option>
									<option value="3">Khác</option>
								</select>
							</div>
						</div>
						<input type="submit" value="Đăng ký">
					</form>
				</div>
			</div>
			<div class="bottom-form">
				<p>
					Bạn có tài khoản? <a href="Login.jsp" class="login">Đăng nhập</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>