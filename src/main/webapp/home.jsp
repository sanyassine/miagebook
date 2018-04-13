<%@ page import="beans.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<html>
	<head>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/home.css" rel="stylesheet">
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</head>


<body>
	<header>
      <!-- Fixed navbar -->
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="home">Home</a>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="friends">Friends <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="disconnect">Log out</a>
            </li>
          </ul>
          <form class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>
    </header>
    <!-- Begin page content -->
    <main role="main" class="container">

	<%
		List<Post> posts = (List) request.getAttribute("posts");
		Map<Post,List<Comment>> commentByPost = (Map) request.getAttribute("commentsByPost");
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		String login = user.getLogin();
		String fName = user.getFirstName();
		String lName = user.getLastName();
		String email = user.getEmail();
	%>
	<h2>
		Welcome
		<%
		out.print(fName + " " + lName);
	%>, you are connected as :
		<%
		out.print(login);
		out.print(" (" + email + ")");
	%>
	</h2>
	<form method="post" action="home">
		<div class="form-group">
			<label>Title of yout post</label>
			<textarea placeholder="Title" class="form-control" id="exampleFormControlTextarea1" rows="1" name="titlePost"></textarea>
		    <label for="exampleFormControlTextarea1">Write your post here</label>
		    <textarea placeholder="Content" class="form-control" id="exampleFormControlTextarea1" rows="3" name="contentPost"></textarea>
		    <button type="submit" value="submit" class="btn btn-primary">New Post</button>
	  	</div>
  </form>

	<h2>List of Posts :</h2>
	
	<div class="list-group">
	<%
		if(posts != null){
		for (Post p : posts) {
			List<Comment> comments = commentByPost.get(p);
			%>
	
		  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
		    <div class="d-flex w-100 justify-content-between">
		      <h5 class="mb-1"><% out.print(p.getTitle()); %></h5>
		      <small class="text-muted"><% out.print(p.getDate()); %></small>
		    </div>
		    <p class="mb-1"><%out.print(p.getContent()); %></p>
		    <small class="text-muted"><%out.print(p.getAuthorLogin()); %></small></br>
		    <form method="post" action="home">
				<div class="form-group">
					<label>Title of yout post</label>
					<input class="invisible" type="text" name="idpostcomment" value="<% out.print(p.getIdPost()); %>">
					<textarea class="form-control" rows="1" name="contentComment" placeholder="write your comment here"></textarea>
				    <button type="submit" value="submit" class="btn btn-primary">Send Comment</button>
			  	</div>
  			</form>
		    <ul>
		    	<%for(Comment comment : comments){ %>
		    		<li> <% out.print(comment.getContent()+" : "+comment.getDate()); %></li>
		    	<%} %>
		    </ul>
		  </a>
  <%
		}
		}
	%>
  
</div>
	</main>
</body>
</html>