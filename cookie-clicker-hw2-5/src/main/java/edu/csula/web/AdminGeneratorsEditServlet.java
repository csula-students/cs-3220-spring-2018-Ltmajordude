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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String html = "";
		html += "<form method='POST'>";
			html += "<h2>Edit Generator</h2>";
			
			html += "<label for='name'>Name:</label>";
			html += "<textarea name='name'>" + entry.getName() + "</textarea>";
			
			html += "<label for='description'>Description:</label>";
			html += "<textarea name='description'>" + entry.getDescription() + "</textarea>";

			html += "<label for='rate'>Rate:</label>";
			html += "<textarea name='rate'>" + entry.getRate() + "</textarea>";

			html += "<label for='baseCost'>Base Cost:</label>";
			html += "<textarea name='baseCost'>" + entry.getBaseCost() + "</textarea>";
			
			html += "<label for='unlockAt'>Unlock At:</label>";
			html += "<textarea name='unlockAt'>" + entry.getUnlockAt() + "</textarea>";

			html += "<input type='submit' value='Submit'>";
		html += "</form>";
		
		out.println(html);

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
