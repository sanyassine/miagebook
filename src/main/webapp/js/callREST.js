var setupListeners = function() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET","/SimpleServlet/rest/status/user/sanyassine"); //must be modified for all users
	xhr.addEventListener("readystatechange",response);
	//xhr.setRequestHeader("Content-type", "application/json");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); //modification du mimetype de la page
	xhr.send(null);
}

var response = function() {
	if(this.readyState === XMLHttpRequest.DONE && this.status === 200) {	
		var res = JSON.parse(this.responseText);
		var divAllPosts = document.getElementById("list-posts");
		for(var i = 0; i <res.length; i++) {
			var author   = res[i].author_login;
			var content  = res[i].content;
			var datetime = res[i].datetime;
			var title    = res[i].title;
			var li = document.createElement("li");
			var h3 = document.createElement("h3"); //title of post
			h3.innerHTML += title;
			
			var p =  document.createElement("p"); //content of post
			p.innerHTML += content;
			
			li.appendChild(h3);
			li.appendChild(p);
			divAllPosts.appendChild(li);
		}
	}
}

window.addEventListener("load",setupListeners);