<%@ page import="beans.*" %>
<html>

<body>
	<header>
		<h1>Miage Book</h1>
	</header>
	
	
	<%
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		String login = user.getLogin();
		String fName = user.getFirstName();
		String lName = user.getLastName();
	%>
	<h2>Welcome <%out.print(fName+" "+lName); %>, you are connected as : <% out.print(login); %></h2>
	
	<h2>List of Posts :</h2>

</body>
</html>