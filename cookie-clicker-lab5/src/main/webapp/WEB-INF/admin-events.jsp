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

	<%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app.css"/>--%>
	<h1>Cookie Clicker Events Servlet (Lab 5)</h1><p>Table of Events</p>

	<table border='5'>
		<tr>
			<th> Name </th>
			<th> Description </th>
			<th> Trigger At </th>
			<th> [ EDIT or DELETE ] </th>
		</tr>

		<%-- DO TABLE FUNCTIONS!!! --%>

		<%--
		Loop Tag <c:forEach>
			<ul>
			    <c:forEach items="${a.numbers}" var="number">
			        <li>${number}</li>
			    </c:forEach>
			</ul>
		--%>

		<c:forEach items="${events}" var="e">
		<tr>
			<td> ${e.name} </td>
			<td> ${e.description} </td>
			<td> ${e.triggerAt} </td>
			<td>
				<a href="./events/edit?id=${e.id}" >Edit</a>
				 | 
				<a href="./events/delete?id=${e.id}" >Delete</a>
			</td>
		</tr>
		</c:forEach>


	<%--
		for ( Event e : events ) {
			<tr>
				<td> e.getName() </td>
				<td> e.getDescription() </td>
				<td> e.getTriggerAt() </td>
				<td>
					<a href="./events/edit?id=" e.getId() >Edit</a>
					 | 
					<a href="./events/delete?id=" e.getId() >Delete</a>
				</td>
			</tr>
		}
	--%>

	</table>

	<form method='POST'>
			<h2>Add Event</h2>
			
			<label for='name'>Event Name:</label>
			<input name='name' id='name' type='text'>  </input>
			
			<br><label for='description'>Event Description:</label>
			<input name='description' id='description' type='text'>  </input>

			<br><label for='triggerAt'>Event Trigger At:</label>
			<input name='triggerAt' id='triggerAt' type='text'>  </input>
			
			<br><input type='submit' value='Submit'>
	</form>
</body>
</html>
