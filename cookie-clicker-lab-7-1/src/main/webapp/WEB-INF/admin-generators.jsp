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

	<h1>Cookie Clicker Generator Servlet (HW 3)</h1><p>Table of Generators</p>

	<table border='5'>
		<tr>
			<th> Location </th>
			<th> Description </th>
			<th> Bit Rate </th>
			<th> Price </th>
			<th> Unlock At </th>
			<th> [ EDIT or DELETE ] </th>
		</tr>

		<c:forEach items="${generators}" var="g">
		<tr>
			<td> ${g.name} </td>
			<td> ${g.description} </td>
			<td> ${g.rate} </td>
			<td> ${g.baseCost} </td>
			<td> ${g.unlockAt} </td>
			<td>
				<a href="./generators/edit?id=${g.id}" >Edit</a>
				|
				<a href="./generators/delete?id=${g.id}" >Delete</a>
			</td>
		</tr>
		</c:forEach>

	</table>

	<form method='POST'>
			<h2>Add Generator</h2>
			
			<label for='name'>Generator Name:</label>
			<input name='name' id='name' type='text'>  </input>
			
			<br><label for='description'>Generator Description:</label>
			<input name='description' id='description' type='text'>  </input>

			<br><label for='rate'>Generator Rate:</label>
			<input name='rate' id='rate' type='text'>  </input>

			<br><label for='baseCost'>Generator Base Cost:</label>
			<input name='baseCost' id='baseCost' type='text'>  </input>

			<br><label for='unlockAt'>Generator Unlock At:</label>
			<input name='unlockAt' id='unlockAt' type='text'>  </input>
			
			<br><input type='submit' value='Submit'>
	</form>

</body>
</html>
