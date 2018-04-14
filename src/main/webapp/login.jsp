<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/login.css" rel="stylesheet">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</head>
<body class="text-center">
	<%@include  file="header_not_connected.html" %>
	
	<form class="form-signin" action="login" method="post">
      <img class="mb-4" src="img/M.gif" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="input" class="sr-only">Login</label>
      <input type="text" id="inputEmail" name="login" class="form-control" placeholder="Login" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <a href="register">Not registered ?</a>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
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