package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

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

@WebServlet("/admin/events/delete")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsDeleteServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EventsDAO dao = new EventsDAOImpl(new Database());
		int id = Integer.parseInt(request.getParameter("id"));
		dao.remove(id);

		response.sendRedirect("../events");

	}

}
