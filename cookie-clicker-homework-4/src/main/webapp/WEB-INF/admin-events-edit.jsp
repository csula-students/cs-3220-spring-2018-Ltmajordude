<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lab 5</title>
</head>
<body>
	<%-- <c:out value="Hello JSTL -MESSAGE" /> --%>

	<%-- <link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/app.css'/> --%>

	<form method='POST'>
		<h2>Edit Event</h2>
			
		<label for='name'>Name:</label>
		<textarea name='name'> ${entry.name} </textarea>
			
		<label for='description'>Description:</label>
		<textarea name='description'> ${entry.description} </textarea>

		<label for='triggerAt'>TriggerAt:</label>
		<textarea name='triggerAt'> ${entry.triggerAt} </textarea>
			
		<input type='submit' value='Submit'>
	</form>

	
</body>
</html>
