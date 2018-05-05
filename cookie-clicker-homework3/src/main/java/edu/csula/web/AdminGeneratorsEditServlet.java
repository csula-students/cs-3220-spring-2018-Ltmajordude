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

@WebServlet("/admin/generators/edit")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminGeneratorsEditServlet extends HttpServlet {
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Generator> entries = (List<Generator>) getServletContext().getAttribute("generators");

		int id = Integer.parseInt(request.getParameter("id"));

		Generator entry = null;

		for (Generator g: entries) {
			if (g.getId() == id) {
				entry = g;
			}
		}
		

		request.setAttribute("entry", entry);
        request.getRequestDispatcher("/WEB-INF/admin-generators-edit.jsp").forward(request, response);
		
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Generator> entries = (List<Generator>) getServletContext().getAttribute("generators");

		int id = Integer.parseInt(request.getParameter("id"));

		Generator entry = null;

		for (Generator g: entries) {
			if (g.getId() == id) {
				entry = g;
			}
		}
		

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlockAt"));

		entry.setName(name);
		entry.setDescription(description);
		entry.setRate(rate);
		entry.setBaseCost(baseCost);
		entry.setUnlockAt(unlockAt);

		response.sendRedirect("../generators");

	}
}
