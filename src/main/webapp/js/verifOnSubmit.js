var setupListeners = function() {
	var inputLogin = document.getElementById("login");
	inputLogin.addEventListener("keyup",onKeyPressed);
}


var onKeyPressed = function() {
	var login = this.value;
	var xhr = new XMLHttpRequest();
	xhr.open("GET","/SimpleServlet/rest/login/".concat(login));
	xhr.addEventListener("readystatechange",response);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); //modification du mimetype de la page
	xhr.send(null);
}


var response = function() {
	if(this.readyState === XMLHttpRequest.DONE && this.status === 200) {
		var res = JSON.parse(this.responseText);
		if(res[0].exists == true) {
			var div = document.getElementById("loginRegister");
			if(!document.getElementById("errorLogin")) {
				var errorSpan = document.createElement("span");
				errorSpan.id = "errorLogin";
				errorSpan.innerHTML += "Login already exists";
				div.appendChild(errorSpan);
			}
		}
	}
}

window.addEventListener("load",setupListeners);