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
	<h2>You are connected as : <% out.print(login); %></h2>

</body>
</html>