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

	<form method='POST'>
		<h2>Edit Generator</h2>
			
		<label for='name'>Name:</label>
		<textarea name='name'>${entry.name}</textarea>
			
		<label for='description'>Description:</label>
		<textarea name='description'>${entry.description}</textarea>

		<label for='rate'>Rate:</label>
		<textarea name='rate'>${entry.rate}</textarea>

		<label for='baseCost'>BaseCost:</label>
		<textarea name='baseCost'>${entry.baseCost}</textarea>

		<label for='unlockAt'>unlockAt:</label>
		<textarea name='unlockAt'>${entry.unlockAt}</textarea>
			
		<input type='submit' value='Submit'>
	</form>

</body>
</html>
