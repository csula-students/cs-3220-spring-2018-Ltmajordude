package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {

	private HttpSession context;

	public void init() {

		//UsersDAO dao = new UsersDAOImpl(context);
		//dao.authenticate("USERNAME1", "PASSWORD1");
		//getServletContext().setAttribute("user", dao);


		/*
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
			events.add( new Event(events.size(), "Ponyville Polls", "+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.", 60) );
			events.add( new Event(events.size(), "Twilight Award", "++ You obtained the \"Most Outstanding Hotel Company\" award from Princess Twilight Sparkle.", 300) );
			events.add( new Event(events.size(), "Celestia Review", "+++ Princess Celestia gave a rave review to one of your hotel.", 600) );
		getServletContext().setAttribute("events", events);
		*/

		//set the username and password to the ones stated in the .md file

		/*
		UsersDAO dao = new UsersDAOImpl(getSessionContext());
		dao.authenticate("USERNAME", "PASSWORD");
		getServletContext().setAttribute("user", dao);
		*/

	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO: render the authentication page HTML

		// Activate Display PrintWriter
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String output = "";

		output += "<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/app.css'/>";
		output += "<h1>Cookie Clicker Login Servlet (Homework 2)</h1>";

		// Username/Password/Login FUNCTIONS
		output += "<form method='POST'>";
			
			output += "<br><label for='username'>Username:</label>";
			output += "<br><input name='username' id='username' type='text'>  </input><br>";
			
			output += "<br><label for='password'>Password:</label>";
			output += "<br><input name='password' id='password' type='text'>  </input><br>";
			
			output += "<br><input type='submit' value='Log In'>";

		output += "</form>";
		
		out.println(output);
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login

		//Duplicate user collection
		UsersDAO dao = (UsersDAO) getServletContext().getAttribute("user");

		//Obtain parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//Check if parameters are valid
		//If valid, redirect to events
		//response.sendRedirect("./events");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(dao.getAuthenticatedUser());

		/*
		// Duplicate event collection
		Collection<Event> entries = (Collection<Event>) getServletContext().getAttribute("events");
		
		// Use parameters to add new event
		entries.add( new Event(entries.size(), name, description, triggerAt) );

		// Update event collection
		response.sendRedirect("./events");
		*/

	}

    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle logout

    	// I am unable to figure out how would I exactly call this. Would I need to have DELETE form methods in other servlets?

    }
}
