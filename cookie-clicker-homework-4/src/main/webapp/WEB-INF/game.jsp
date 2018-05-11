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

	<script>

		/*
		Once you have the JSON string, you can pass the JSON string 
		   back to the game.jsp file in the request scope and render 
		   such JSON string in the script tag to pass JSON information 
		   to JavaScript.
		*/

	    window.game = {};
	    window.game.state = ${state}; // where state is passed from Controller as JSON string


	    //Besides using the example coding, I am unable to understand how to proceed further with this part.
	    
	</script>

</body>
</html>
