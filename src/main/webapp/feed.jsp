<%@ page import="beans.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<html>
	<head>
		<link href="css/bootstrap.min.css" rel="stylesheet"/>
		<link href="css/home.css" rel="stylesheet"/>
		<script type="text/javascript" src="js/feedCallRest.js"></script>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</head>


	<body>
		<%@include  file="header_connected.html" %>
	    <!-- Begin page content -->
	    <main role="main" class="container">
			<h1>Feed</h1>
			
			<% String login = (String)request.getAttribute("login");%>
			<span id="login_data" class="invisible"><% out.print(login);%></span>
			
		 	<!--  post retrieved by rest  -->
		  	<div id="all-posts">
		  	<h2>List of Posts (REST)</h1>
			 
		  	</div>
		</main>
	</body>
</html>