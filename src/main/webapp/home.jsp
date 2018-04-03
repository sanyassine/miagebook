<%@ page import="beans.*" %>
<html>

<body>
	<header>
		<h1>Miage Book</h1>
	</header>
	
	
	<%
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		String login = user.getLogin();
	%>
	<h2>Welcome, you are connected as : <% out.print(login); %></h2>
	
	<h2>List of Posts :</h2>

</body>
</html>