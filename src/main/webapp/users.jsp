<%@ page import="beans.*" %>
<%@ page import="java.util.List" %>
<html>
	<head>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/home.css" rel="stylesheet">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script src="js/friends	.js"></script>
	</head>
<body>
	<%@include  file="header_connected.html" %>
    <main role="main" class="container">
    
    <%
    	List<Profile> users = (List<Profile>) request.getAttribute("users");
    	UserProfile user = (UserProfile) request.getSession().getAttribute("user");
    	
		String login = user.getLogin();
    %>
    <span id="login_data" class="invisible"><% out.print(login);%></span>
    
	<h3>Users: </h3>
	
	<ul class="list-group">
		  
	<%
		for(Profile p : users){ %>
		<%if(!p.getLogin().equals(user.getLogin())){ %>
			<li class="list-group-item"><%out.print(p.getLogin()); 
					
				//to see if the user is connected
				 if(p.isConnected()) {
					%>
					<span>(connected)</span>
				<%}
				else {	%>
					<span>(not connected)</span>
				<% }%>
				
				<p>Last connection: <% out.print(p.getLastConnection());%></p> 
				
				<a href="feed?login=<%out.print(p.getLogin());%>">See feed</a>
				
				<%if (user.isFriendsWith(p.getLogin())){ %>
					<!-- <form method="post" action="users">
						<input type="hidden" type="text" name="loginRemove" value="<% out.print(p.getLogin()); %>">-->
						<button type="submit" value="submit" id="<%out.print(p.getLogin());%>">remove from friends</button>
					<!-- </form> -->
				<% }else{ %>
					<!--  <form method="post" action="users">
						<input type="hidden" type="text" name="loginAdd" value="<% out.print(p.getLogin()); %>">-->
						<button type="submit" value="submit">add to friends</button>
					<!-- </form> -->
				<% }%>
			</li>
		<%}
		}
	%>
	</ul>
	</main>
</body>
</html>
