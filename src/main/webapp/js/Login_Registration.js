function checkLogin() {
	var username = document.formLogin.username.value;
	var password = document.formLogin.password.value;
	if (username == "" && password == "") {
		alert("Please enter username and password");
	}
	else if (username == "" && password != "") {
		alert("Please enter username");
	}
	else if (username != "" && password == "") {
		alert("Please enter password");
	}
}
function togglePassword(typeToggle) {
	switch (typeToggle) {
		case "password":
			var type = document.getElementById("input-password").type;
			document.getElementById("input-password").type = (type == "password") ? "text" : "password";
			document.getElementById("eye").className = (type == "password") ? "fas fa-eye" : "fas fa-eye-slash";
			break;
		case "oldPassword":
			var typeOldPassword = document.getElementById("input-oldpassword").type;
			document.getElementById("input-oldpassword").type = (typeOldPassword == "password") ? "text" : "password";
			document.getElementById("eye-oldpassword").className = (typeOldPassword == "password") ? "fas fa-eye" : "fas fa-eye-slash";
			break;
		case "newPassword":
			var typeNewPassword = document.getElementById("input-newpassword").type;
			document.getElementById("input-newpassword").type = (typeNewPassword == "password") ? "text" : "password";
			document.getElementById("eye-newpassword").className = (typeNewPassword == "password") ? "fas fa-eye" : "fas fa-eye-slash";
			break;
		case "confirmPassword":
			var typeConfirmPassword = document.getElementById("input-confirmpassword").type;
			document.getElementById("input-confirmpassword").type = (typeConfirmPassword == "password") ? "text" : "password";
			document.getElementById("eye-confirmpassword").className = (typeConfirmPassword == "password") ? "fas fa-eye" : "fas fa-eye-slash";
			break;
	}
}
