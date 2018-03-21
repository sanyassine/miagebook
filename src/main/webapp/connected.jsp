<html>

<body>
	<h1> You are connected </h1>
	
	<%@ page import="beans.*" %>
	<%
		String username = request.getParameter("login");
		out.print(username);
	%>

</body>
</html>