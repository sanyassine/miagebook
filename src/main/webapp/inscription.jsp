<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/login.css" rel="stylesheet">
		<script src="js/verifOnSubmit.js"></script>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<title>Inscription</title>
	</head>
<body>
	<%@include  file="header_not_connected.html" %>
	<form method="post" class="form-signin">
		<div class="form-group">
			<label>First Name</label> <input name="firstname"
				type="text" class="form-control" id="fName"
			 placeholder="Firstname">
		</div>
		<div class="form-group">
			<label>Last Name</label> <input name="lastname"
				type="text" class="form-control" id="lName"
				placeholder="Lastname">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Email</label> <input name="email"
				type="email" class="form-control" id="email"
			 placeholder="Email">
		</div>
		<div class="form-group" id="loginRegister">
			<label>Login</label> <input name="login"
				type="text" class="form-control" id="login"
				placeholder="Login">
		</div>
		<div class="form-group">
			<label>Password</label> <input name="password"
				type="password" class="form-control" id="password"
				placeholder="Password">
		</div>
		
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	<%List<String> errorMessages = (List<String>)request.getSession().getAttribute("error_message"); %>
	
	<% if(errorMessages != null){
		for(String msg : errorMessages){
		out.print(msg);
	}errorMessages.clear();}%>
</body>
</html>