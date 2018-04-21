package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

//@WebServlet("/admin/events")
@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsServlet extends HttpServlet {

	public void init() {
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
			events.add( new Event(events.size(), "Ponyville Polls", "+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.", 60) );
			events.add( new Event(events.size(), "Twilight Award", "++ You obtained the \"Most Outstanding Hotel Company\" award from Princess Twilight Sparkle.", 300) );
			events.add( new Event(events.size(), "Celestia Review", "+++ Princess Celestia gave a rave review to one of your hotel.", 600) );
		getServletContext().setAttribute("events", events);
	}
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Activate Display PrintWriter
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Obtain event collection
		Collection<Event> events = (Collection<Event>) getServletContext().getAttribute("events");
		System.out.println(events);
		String output = "";

		// Start text output
		output += "<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/app.css'/>";
		
		output += "<h1>Cookie Clicker Events Servlet (Lab 4)</h1><p>Table of Events</p>";
			output += "<form method='DELETE'>";
			output += "<a href='./auth'><input type='submit' value='Log Out'></a>";
			output += "</form>";
		output += "<a href='./generators'>Click here for Generators</a>";

		//Table of Events
		output += "<table border='5'>";
		output += "<tr>";
		output += "<th> Name </th>"
				+ "<th> Description </th>"
				+ "<th> Trigger At </th>"
				+ "<th> [ EDIT or DELETE ] </th>";
		output += "</tr>";
			
		for ( Event e : events ) {
			output += "<tr>";
				output += "<td> " + e.getName() + " </td>"
						+ "<td> " + e.getDescription() + " </td>"
						+ "<td> " + e.getTriggerAt() + " </td>"
						+ "<td>"
						+ "<a href='./events/edit?id=" + e.getId() + "'>Edit</a>"
						+ " | "
						+ "<a href='./events/delete?id=" + e.getId() + "'>Delete</a>"
						+ "</td>";
			output += "</tr>";
		}
		
		output += "</table>";

		// New Events FUNCTION
		output += "<form method='POST'>";
			output += "<h2>Add Event</h2>";
			
			output += "<label for='name'>Event Name:</label>";
			output += "<input name='name' id='name' type='text'>  </input>";
			
			output += "<br><label for='description'>Event Description:</label>";
			output += "<input name='description' id='description' type='text'>  </input>";

			output += "<br><label for='triggerAt'>Event Trigger At:</label>";
			output += "<input name='triggerAt' id='triggerAt' type='text'>  </input>";
			
			output += "<br><input type='submit' value='Submit'>";
		output += "</form>";

		out.println(output);

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Duplicate event collection
		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");

		// Obtain parameters
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int triggerAt = Integer.parseInt(request.getParameter("triggerAt"));
		
		// Use parameters to add new event
		entries.add( new Event(entries.size(), name, description, triggerAt) );

		// Update event collection
		response.sendRedirect("./events");

	}
}
