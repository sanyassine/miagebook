var setupListeners = function() {
	var btns = document.getElementsByTagName("button");
	for(var i = 0; i < btns.length;i++) {
		btns[i].addEventListener("click",traitement);
	}
}

var traitement = function() {
	var login = document.getElementById("login_data").innerHTML;
	if(login != undefined){
		var xhr = new XMLHttpRequest();
		xhr.open("GET","/SimpleServlet/rest/friends/"+login+"/"+this.id); //must be modified for all users
		xhr.addEventListener("readystatechange",response);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); //modification du mimetype de la page
		xhr.send(null);
	}
}

var response = function() {
	if(this.readyState === XMLHttpRequest.DONE && this.status === 200) {
		var res = JSON.parse(this.responseText);
		var btn = document.getElementById(res[0].loginFriend);
		var bool = res[0].add_friend;
		if(bool == false) {
			btn.innerHTML = "add from friend";
			
		}
		else if(bool == true) {
			btn.innerHTML = "remove from friend";
		}
	}
}

window.addEventListener("load",setupListeners);