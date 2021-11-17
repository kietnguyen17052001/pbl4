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
