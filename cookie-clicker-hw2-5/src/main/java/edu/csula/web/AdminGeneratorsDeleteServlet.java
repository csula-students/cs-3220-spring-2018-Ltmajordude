package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

@WebServlet("/admin/generators/delete")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminGeneratorsDeleteServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Generator> entries = (List<Generator>) getServletContext().getAttribute("generators");
		
		int id = Integer.parseInt(request.getParameter("id"));

		for (int i = 0 ; i < entries.size(); i++) {
			if (entries.get(i).getId() == id)
				entries.remove(i);
		}

		response.sendRedirect("../generators");

	}

}
