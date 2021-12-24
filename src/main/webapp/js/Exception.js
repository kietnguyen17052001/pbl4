// check username and password when login
function checkLogin() {
	var _username = document.getElementById("username").value;
	var _password = document.getElementById("input-password").value;
	if (_username == "" || _password == "") {
		if (_username == "") {
			document.getElementById("username").style.borderColor = "red";
		}
		if (_password == "") {
			document.getElementById("input-password").style.borderColor = "red";
		}
		document.getElementById("failed").innerHTML = "Please enter information!"
		return false;
	}
}

// check username
function invalidUsername(username) {
	for (var i = 0; i < username.length; i++) {
		if (username.charAt(i) == username.charAt(i).toUpperCase()) {
			return false;
		}
	}
	return true;
}

// check information when registration
function checkRegistration() {
	var lastname = document.formRegistration.lastname.value;
	var firstname = document.formRegistration.firstname.value;
	var email = document.formRegistration.email.value;
	var phone = document.formRegistration.phone.value;
	var username = document.formRegistration.username.value;
	var password = document.formRegistration.password.value;
	if (lastname == "" || firstname == "" || email == "" || phone == "" || username == "" || password == "" || invalidUsername(username) == false) {
		if (lastname == "") {
			document.getElementById("lastname").style.borderColor = "red";
		}
		if (firstname == "") {
			document.getElementById("firstname").style.borderColor = "red";
		}
		if (email == "") {
			document.getElementById("email").style.borderColor = "red";
		}
		if (phone == "") {
			document.getElementById("phone").style.borderColor = "red";
		}
		if (username == "" || invalidUsername(username) == false) {
			document.getElementById("username").style.borderColor = "red";
			if (invalidUsername(username) == false && username != "") {
				alert("Invalid username (Only letters lowercase)!");
				return false;
			}
		}
		if (password == "") {
			document.getElementById("input-password").style.borderColor = "red";
		}
		alert("Please enter full information!");
		return false;
	}
}

// check forgot password
function checkForgotPassword() {
	var email = document.formForgotPassword.email.value;
	var phone = document.formForgotPassword.phone.value;
	if (email == "" || phone == "") {
		if (email == "") {
			document.getElementById("email").style.borderColor = "red";
		}
		if (phone == "") {
			document.getElementById("phone").style.borderColor = "red";
		}
		return false;
	}
}

// check change password
function checkChangePassword() {
	var oldPassword = document.formChangePassword.oldpassword.value;
	var newPassword = document.formChangePassword.newpassword.value;
	var confirmPassword = document.formChangePassword.confirmpassword.value;
	if (oldPassword == "" || newPassword == "" || confirmPassword == "" || (confirmPassword != newPassword && confirmPassword != "" && newPassword != "")) {
		if (oldPassword == "") {
			document.getElementById("input-oldpassword").style.borderColor = "red";
		}
		if (newPassword == "") {
			document.getElementById("input-newpassword").style.borderColor = "red";
		}
		if (confirmPassword == "") {
			document.getElementById("input-confirmpassword").style.borderColor = "red";
		}
		if (confirmPassword != newPassword && confirmPassword != "" && newPassword != "") {
			alert("Invalid confirm password");
			document.getElementById("input-confirmpassword").style.borderColor = "red";
		}
		return false;
	}
}

// check edit information
function checkEditInformation() {
	var lastname = document.formEditInformation.lastname.value;
	var firstname = document.formEditInformation.firstname.value;
	var email = document.formEditInformation.email.value;
	var phone = document.formEditInformation.phone.value
	if (lastname == "" || firstname == "" || email == "" || phone == "") {
		if (lastname == "") {
			document.getElementById("lastname").style.borderColor = "red";
		}
		if (firstname == "") {
			document.getElementById("firstname").style.borderColor = "red";
		}
		if (email == "") {
			document.getElementById("email").style.borderColor = "red";
		}
		if (phone == "") {
			document.getElementById("phone").style.borderColor = "red";
		}
		return false;
	}
}


