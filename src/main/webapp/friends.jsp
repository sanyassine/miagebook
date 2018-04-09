<%@ page import="beans.*" %>
<%@ page import="java.util.List" %>
<html>
	<head>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</head>
<body>
	
	<h1>My profil</h1>
	
	
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
	<%
		for(Profile p : friends){
			out.print(p); %> </br><%
		}
	%>

</body>
</html>
