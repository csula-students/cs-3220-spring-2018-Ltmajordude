package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import edu.csula.storage.servlet.EventsDAOImpl;
//import edu.csula.storage.EventsDAO;
//import edu.csula.models.Event;
import edu.csula.models.Event;
import edu.csula.storage.EventsDAO;
import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.mysql.Database;

@WebServlet("/admin/events/edit")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsEditServlet extends HttpServlet {
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");

		int id = Integer.parseInt(request.getParameter("id"));

		Event entry = null;

		for (Event e: entries) {
			if (e.getId() == id) {
				entry = e;
			}
		}
		*/

		int id = Integer.parseInt(request.getParameter("id"));

		EventsDAO dao = new EventsDAOImpl(new Database());

		//Event entry = dao.getById(id);

		request.setAttribute("entry", dao.getById(id));
        request.getRequestDispatcher("/WEB-INF/admin-events-edit.jsp").forward(request, response);

		/*
		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");

		int id = Integer.parseInt(request.getParameter("id"));

		Event entry = null;

		for (Event e: entries) {
			if (e.getId() == id) {
				entry = e;
			}
		}
		*/

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int triggerAt = Integer.parseInt(request.getParameter("triggerAt"));

		EventsDAO dao = new EventsDAOImpl(new Database());

		/*
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
		*/

	}
}
