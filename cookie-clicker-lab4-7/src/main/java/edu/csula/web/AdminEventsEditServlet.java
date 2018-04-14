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

@WebServlet("/admin/events/edit")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsEditServlet extends HttpServlet {
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");

		int id = Integer.parseInt(request.getParameter("id"));

		Event entry = null;

		for (Event e: entries) {
			if (e.getId() == id) {
				entry = e;
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String html = "";
		html += "<form method='POST'>";
			html += "<h2>Edit Event</h2>";
			
			html += "<label for='name'>Name:</label>";
			html += "<textarea name='name'>" + entry.getName() + "</textarea>";
			
			html += "<label for='description'>Description:</label>";
			html += "<textarea name='description'>" + entry.getDescription() + "</textarea>";

			html += "<label for='triggerAt'>TriggerAt:</label>";
			html += "<textarea name='triggerAt'>" + entry.getTriggerAt() + "</textarea>";
			
			html += "<input type='submit' value='Submit'>";
		html += "</form>";
		
		out.println(html);

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");

		int id = Integer.parseInt(request.getParameter("id"));

		Event entry = null;

		for (Event e: entries) {
			if (e.getId() == id) {
				entry = e;
			}
		}

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int triggerAt = Integer.parseInt(request.getParameter("triggerAt"));

		entry.setName(name);
		entry.setDescription(description);
		entry.setTriggerAt(triggerAt);

		response.sendRedirect("../events");

	}
}
