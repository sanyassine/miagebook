<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> Register </h1>
	
	<form method="post">
		<span>firstname</span><br>
		<input type="text" name="firstname"><br>
		
		<span>lastname</span><br>
		<input type="text" name="lastname"><br>
		
		<span>mail :</span><br>
		<input type="text" name="email"><br>
		
		<span>login :</span> <br>
		<input type="text" name="login"><br>
		
		<span>password :</span><br>
		<input type="password" name="password"><br>
		
		<input type="submit" value="Submit">
	</form>
	
	<h1> Login </h1>
	
	<form method="post" action="connect">
		<span>login :</span> <br>
		<input type="text" name="login"><br>
		
		<span>password :</span><br>
		<input type="password" name="password"><br>
		
		<input type="submit" value="Submit">
	</form>
	
	<%
	List<String> errorMessage = (List) request.getSession().getAttribute("error_message");
	if(errorMessage != null){
		for(String msg : errorMessage){ %>
	<h4>
		<%out.print(msg); %>
	</h4>
	
	<%	} 
	}
	request.getSession().setAttribute("error_message",null);%>
</body>
</html>