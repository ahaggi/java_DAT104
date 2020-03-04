<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
        <title>Login</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
		<link rel="stylesheet" href="./css/bulma.css">
	</head>
	<body>
		<section class="hero is-fullheight">
	  		<div class="hero-body">
				<div class="container">
					<div class="columns is-vcentered">
			        	<div class="column is-4 is-offset-4">
			            	<div class="box">
			            		<h1 class="title is-3">Login</h1>
			            		<form action="login" method="post" role="form">
				              		<label class="label">Email</label>
				              		<p class="control">
				                		<input class="input" name="username" type="text" placeholder="example@example.org">
				              		</p>
				              		<label class="label">Password</label>
				              		<p class="control">
				                		<input class="input" name="password" type="password" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
				              		</p>
				              		<c:if test="${not empty errorMsg}">
					              		<div class="notification is-danger">
											${errorMsg}
										</div>
									</c:if>
				              		<hr>
				              		<p class="control">
				                		<button type="submit" class="button is-primary">Login</button>
				              		</p>
			              		</form>
			              	</div>
			        	</div>
			        </div>
		        </div>
			</div>
		</section>
	</body>
</html>
