<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/login.css" rel="stylesheet">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<title>Inscription</title>
	</head>
<body>
	<header>
      <!-- Fixed navbar -->
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="login">Login <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="register">Register <span class="sr-only">(current)</span></a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
	
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
		<div class="form-group">
			<label>Login</label> <input name="login"
				type="text" class="form-control" id="login"
				placeholder="Password">
		</div>
		<div class="form-group">
			<label>Last Name</label> <input name="password"
				type="password" class="form-control" id="password"
				placeholder="Password">
		</div>
		
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>