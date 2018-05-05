package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();

		//DTO dto = new DTO();
        //GsonBuilder builder = new GsonBuilder();
        //Gson gson = builder.create();
        //String jsonString = gson.toJson(dto);

		// Obtain event collection
		List<Generator> generators = (List<Generator>) getServletContext().getAttribute("generators");
		//GeneratorsDAO generators = (GeneratorsDAO) getServletContext().getAttribute("generators");
		System.out.println(generators);

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(generators);

		request.setAttribute("generators", generators);
        request.getRequestDispatcher("/WEB-INF/admin-generators.jsp").forward(request, response);

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction

		// Duplicate event collection
		List<Generator> entries = (List<Generator>) getServletContext().getAttribute("generators");
		GeneratorsDAO dao = (GeneratorsDAO) getServletContext().getAttribute("generators");

		// Obtain parameters
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlockAt"));
		
		// Use parameters to add new event
		//entries.add( new Generator(entries.size(), name, description, rate, baseCost, unlockAt) );
		dao.add( new Generator(entries.size(), name, description, rate, baseCost, unlockAt) );

		// Update event collection
		response.sendRedirect("./generators");

	}
}
