<html>

<body>
	<header>
		<h1>Miage Book</h1>
	</header>
	<%@ page import="beans.*" %>
	<h1> You are connected </h1>
	
	<%
		String username = ((UserProfile) request.getSession().getAttribute("user")).getLogin();
		out.print(username);
	%>

</body>
</html>