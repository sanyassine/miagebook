var setupListeners = function() {
	var login = provideLogin();
	if(login != undefined){
		var xhr = new XMLHttpRequest();
		xhr.open("GET","/SimpleServlet/rest/status/user/".concat(login)); //must be modified for all users
		xhr.addEventListener("readystatechange",response);
		//xhr.setRequestHeader("Content-type", "application/json");
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); //modification du mimetype de la page
		xhr.send(null);
	}
}

var provideLogin = function(){
	return document.getElementById("login_data").innerHTML;
}

var response = function() {
	if(this.readyState === XMLHttpRequest.DONE && this.status === 200) {	
		var res = JSON.parse(this.responseText);
		var divAllPosts = document.getElementById("all-posts");
		for(var i = 0; i <res.length; i++) {
			var author   = res[i].author_login;
			var content  = res[i].content;
			var datetime = res[i].datetime;
			var title    = res[i].title;
			var idPost   = res[i].id_post;
			var comments = res[i].comments;
			
			var a = document.createElement("a");
			a.className = "list-group-item list-group-item-action flex-column align-items-start";
			
			a.appendChild(createTitlePart(title,datetime));
			a.appendChild(createContentPart(content));
			a.appendChild(createAuthorPart(author));
			a.appendChild(createAddCommentPart(idPost));
			a.appendChild(createCommentsPart(comments));
			
			divAllPosts.appendChild(a);
		}
	}
}

var createTitlePart = function(title, datetime){
	var divTitle = document.createElement("div");
	divTitle.className = "d-flex w-100 justify-content-between";
	
	var h5Title = document.createElement("h5");
	h5Title.className="mb-1";
	h5Title.innerHTML = title;
	
	var smallDate = document.createElement("small");
	smallDate.className="text-muted";
	smallDate.innerHTML = datetime;
	
	divTitle.appendChild(h5Title);
	divTitle.appendChild(smallDate);
	return divTitle;
}

var createContentPart = function(content){
	var pContent = document.createElement("p");
	pContent.className="mb-1";
	pContent.innerHTML = content;
	return pContent;
}

var createAuthorPart = function(author){
	var smallAuthorLogin = document.createElement("small");
	smallAuthorLogin.className="text-muted";
	smallAuthorLogin.innerHTML = author;
	return smallAuthorLogin;
}

var createAddCommentPart = function(idPost){
	var form = document.createElement("form");
	form.method="post";
	form.action ="home";
	
	var divForm = document.createElement("div");
	divForm.className="form-group";
	
	var labelForm = document.createElement("label");
	labelForm.innerHTML = "Title of yout post";
	
	var invisibleInputForIdPost = document.createElement("input");
	invisibleInputForIdPost.className="invisible";
	invisibleInputForIdPost.type="text";
	invisibleInputForIdPost.name="idpostcomment";
	invisibleInputForIdPost.value=idPost;
	
	var textAreaComment = document.createElement("textarea");
	textAreaComment.className="form-control";
	textAreaComment.name="contentComment";
	textAreaComment.placeholder="write yout comment here";
	
	var buttonSend = document.createElement("button");
	buttonSend.type="submit";
	buttonSend.value="submit";
	buttonSend.className="btn btn-primary";
	buttonSend.innerHTML="Send Comment";
	
	divForm.appendChild(labelForm);
	divForm.appendChild(invisibleInputForIdPost);
	divForm.appendChild(textAreaComment);
	divForm.appendChild(buttonSend);
	
	form.appendChild(divForm);
	return form;
}

var createCommentsPart = function(comments){
	var listComment = document.createElement("ul");
	for(var i = 0 ; i < comments.length ; i = i + 1){
		var com = comments[i];
		var author = com.author;
		var datetime = com.datetime;
		var content = com.content;
		
		var li = document.createElement("li");
		li.innerHTML = author.concat(" : ").concat(content).concat(" - ").concat(datetime);
		listComment.appendChild(li);
	}
	return listComment;
}

window.addEventListener("load",setupListeners);