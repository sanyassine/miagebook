<%@ page import="beans.*" %>
<html>
<body>
	
	<h1>My profil</h1>
	
	
	<%
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		String login = user.getLogin();
		String fName = user.getFirstName();
		String lName = user.getLastName();
		String email = user.getEmail();
	%>
	
	<h3>First name: <% out.print(fName); %></h3>
	<h3>Last name:  <% out.print(lName);%></h3>
	<h3>My friends: </h3>
	<ul></ul>

</body>
</html>
