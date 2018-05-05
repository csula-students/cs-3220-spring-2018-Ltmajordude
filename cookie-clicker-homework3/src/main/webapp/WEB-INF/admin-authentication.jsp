<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>HW 3</title>
</head>
<body>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app.css" />

	<h1>Cookie Clicker Login Servlet (Homework 3)</h1>

	<form method='POST'>
			
		<br><label for='username'>Username:</label>
		<br><input name='username' id='username' type='text'>  </input><br>
		
		<br><label for='password'>Password:</label>
		<br><input name='password' id='password' type='text'>  </input><br>
		
		<br><input type='submit' value='Log In'>

	</form>

</body>
</html>
