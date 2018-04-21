package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {

	public void init() {
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		List<Generator> generators = dao.getAll();
			generators.add( new Generator( generators.size(), "Ponyville", "Home of Princess Twilight Sparkle of Friendship, the Ambassadors of Harmony, Twilight Sparkle's apprentice Starlight Glimmer and Spike the Dragon, hero of the Crystal Empire.", 5, 10, 10 ) );
			generators.add( new Generator( generators.size(), "Canterlot", "Home of Princess Celestia of the Sun and Princess Luna of the Moon.", 10, 100, 25 ) );
			generators.add( new Generator( generators.size(), "Crystal Empire", "Home of Princess Cadance of Love and Captain Shining Armor, commander of the Royal Guard.", 20, 500, 50 ) );
		getServletContext().setAttribute("generators", generators);
	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Activate Display PrintWriter
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Obtain event collection
		List<Generator> generators = (List<Generator>) getServletContext().getAttribute("generators");
		System.out.println(generators);
		String output = "";

		// Start text output
		output += "<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/app.css'/>";
		output += "<h1>Cookie Clicker Generators Servlet (Homework 2)</h1><p>Table of Generators</p>";
			output += "<form method='DELETE'>";
			output += "<a href='./auth'><input type='submit' value='Log Out'></a>";
			output += "</form>";
		output += "<a href='./events'>Click here for Events</a>";

		//Table of Events
		output += "<table border='5'>";
		output += "<tr>";
		output += "<th> Location </th>"
				+ "<th> Description </th>"
				+ "<th> Bit Rate </th>"
				+ "<th> Price </th>"
				+ "<th> Unlock At </th>"
				+ "<th> [ EDIT or DELETE ] </th>";
		output += "</tr>";
			
		for ( Generator g : generators ) {
			output += "<tr>";
				output += "<td> " + g.getName() + " </td>"
						+ "<td> " + g.getDescription() + " </td>"
						+ "<td> " + g.getRate() + " </td>"
						+ "<td> " + g.getBaseCost() + " </td>"
						+ "<td> " + g.getUnlockAt() + " </td>"
						+ "<td>"
						+ "<a href='./generators/edit?id=" + g.getId() + "'>Edit</a>"
						+ " | "
						+ "<a href='./generators/delete?id=" + g.getId() + "'>Delete</a>"
						+ "</td>";
			output += "</tr>";
		}
		
		output += "</table>";

		// New Generators FUNCTION
		output += "<form method='POST'>";
			output += "<h2>Add Generator</h2>";

			output += "<label for='name'>Generator Name:</label>";
			output += "<input name='name' id='name' type='text'>  </input>";

			output += "<br><label for='description'>Generator Description:</label>";
			output += "<input name='description' id='description' type='text'>  </input>";

			output += "<br><label for='rate'>Generator Rate:</label>";
			output += "<input name='rate' id='rate' type='text'>  </input>";

			output += "<br><label for='baseCost'>Generator Base Cost:</label>";
			output += "<input name='baseCost' id='baseCost' type='text'>  </input>";			

			output += "<br><label for='unlockAt'>Generator Unlock At:</label>";
			output += "<input name='unlockAt' id='unlockAt' type='text'>  </input>";
			
			output += "<br><input type='submit' value='Submit'>";
		output += "</form>";

		out.println(output);

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction

		// Duplicate event collection
		List<Generator> entries = (List<Generator>) getServletContext().getAttribute("generators");

		// Obtain parameters
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlockAt"));
		
		// Use parameters to add new event
		entries.add( new Generator(entries.size(), name, description, rate, baseCost, unlockAt) );

		// Update event collection
		response.sendRedirect("./generators");

	}
}
