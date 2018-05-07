package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.storage.EventsDAO;
import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.mysql.Database;

//@WebServlet("/admin/events")
@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsServlet extends HttpServlet {

	/*
	public void init() {
		//Database context = new Database();
		EventsDAO dao = new EventsDAOImpl(new Database());
		dao.add( new Event( 0, "Ponyville Polls", "+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.", 60) );
		dao.add( new Event( 1, "Twilight Award", "++ You obtained the \"Most Outstanding Hotel Company\" award from Princess Twilight Sparkle.", 300) );
		dao.add( new Event( 2, "Celestia Review", "+++ Princess Celestia gave a rave review to one of your hotel.", 600) );
	}
	*/
	
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EventsDAO dao = new EventsDAOImpl(new Database());

        request.setAttribute("events", dao.getAll());
        request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
		
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int triggerAt = Integer.parseInt(request.getParameter("triggerAt"));
		
		EventsDAO dao = new EventsDAOImpl(new Database());
		Event event = new Event(dao.getAll().size(), name, description, triggerAt);
		dao.add(event);

		response.sendRedirect("events");

	}
}
