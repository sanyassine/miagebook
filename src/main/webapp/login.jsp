<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> LOGIN </h1>
	
	<form method="post">
		<span>login :</span> <br>
		<input type="text" name="login"><br>
		<span>password :</span><br>
		<input type="password" name="password"><br>
		<input type="submit" value="Submit">
	</form>
	
	<%
	String errorMessage = (String) request.getSession().getAttribute("error_message");
	if(errorMessage != null){ %>
	<h4>
		<%out.print(errorMessage); %>
	</h4>
	
	<%} 
	request.getSession().setAttribute("error_message",null);%>
</body>
</html>