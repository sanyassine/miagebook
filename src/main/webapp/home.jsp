<%@ page import="beans.*" %>
<%@ page import="java.util.List" %>
<html>

<body>
	<header>
		<h1>Miage Book</h1><a href="disconnect">disconnect</a>&nbsp<a href="friends">friends</a>
	</header>
	
	
	<%
		List<Post> posts = (List)request.getAttribute("posts");
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		String login = user.getLogin();
		String fName = user.getFirstName();
		String lName = user.getLastName();
		String email = user.getEmail();
	%>
	<h2>Welcome <%out.print(fName+" "+lName); %>, you are connected as : <% out.print(login); out.print(" ("+email+")"); %></h2>
	
	<h2>List of Posts :</h2>
	<%for(Post p : posts){
		out.print(p);%> </br><%
		
	} %>

</body>
</html>