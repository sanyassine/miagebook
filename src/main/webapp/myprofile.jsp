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
	</head>
<body>
	<%@include  file="header_connected.html" %>
    <main role="main" class="container">
	<h1>My profile</h1>
	
	
	<%
		List<Profile> friends = (List)request.getAttribute("friends");
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		String login = user.getLogin();
		String fName = user.getFirstName();
		String lName = user.getLastName();
		String email = user.getEmail();
	%>
	
	<h3>First name: <% out.print(fName); %></h3>
	<h3>Last name:  <% out.print(lName);%></h3>
	<h3>My friends: </h3>
	
	<ul class="list-group">
		  
	<%
		for(Profile p : friends){
			%><li class="list-group-item"><%out.print(p.getLogin()); %></li><%
		}
	%>
	</ul>
	</main>
</body>
</html>
