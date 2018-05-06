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
import edu.csula.storage.Database;

//@WebServlet("/admin/events")
@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsServlet extends HttpServlet {

	//EventsDAO dao = new EventsDAOImpl(new Database());
	//List<Event> events = dao.getAll();

	/*
	public void init() {
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
			events.add( new Event(events.size(), "Ponyville Polls", "+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.", 60) );
			events.add( new Event(events.size(), "Twilight Award", "++ You obtained the \"Most Outstanding Hotel Company\" award from Princess Twilight Sparkle.", 300) );
			events.add( new Event(events.size(), "Celestia Review", "+++ Princess Celestia gave a rave review to one of your hotel.", 600) );
		getServletContext().setAttribute("events", events);
	}
	*/

	/*
	public void init() {

		super.init( config );

        // load driver
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch( ClassNotFoundException e ) {
            throw new ServletException( e );
        }

	}
	*/
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		doGet(req, res) {
		    data := dao.getAll()
 		   req.put("data", data)
    		res.dispatch("jsp").forward(req, res)
		}
		*/

        //Event events = new Event(0, "Ponyville Polls", "+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.", 60);

		//Collection<Event> events = (Collection<Event>) getServletContext().getAttribute("events");
		/*
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
			events.add( new Event(events.size(), "Ponyville Polls", "+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.", 60) );
			events.add( new Event(events.size(), "Twilight Award", "++ You obtained the \"Most Outstanding Hotel Company\" award from Princess Twilight Sparkle.", 300) );
			events.add( new Event(events.size(), "Celestia Review", "+++ Princess Celestia gave a rave review to one of your hotel.", 600) );
		*/

		//EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = (Collection<Event>) getServletContext().getAttribute("events");

        request.setAttribute("events", events);
        request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
		
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int triggerAt = Integer.parseInt(request.getParameter("triggerAt"));
		
		dao.add( new Event(entries.size(), name, description, triggerAt) );
		*/

		/*

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

		*/

	}
}
