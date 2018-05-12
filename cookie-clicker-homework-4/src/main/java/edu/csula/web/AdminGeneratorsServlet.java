package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;
import edu.csula.storage.mysql.Database;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {

	public void init() {
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		dao.add( new Generator( 0, "Ponyville", "Home of Princess Twilight Sparkle of Friendship, the Ambassadors of Harmony, Twilight Sparkle's apprentice Starlight Glimmer and Spike the Dragon, hero of the Crystal Empire.", 5, 10, 10 ) );
		dao.add( new Generator( 1, "Canterlot", "Home of Princess Celestia of the Sun and Princess Luna of the Moon.", 10, 100, 25 ) );
		dao.add( new Generator( 2, "Crystal Empire", "Home of Princess Cadance of Love and Captain Shining Armor, commander of the Royal Guard.", 20, 500, 50 ) );
	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());

        request.setAttribute("generators", dao.getAll());
        request.getRequestDispatcher("/WEB-INF/admin-generators.jsp").forward(request, response);

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtain parameters
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlockAt"));
		
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		Generator generator = new Generator(dao.getAll().size(), name, description, rate, baseCost, unlockAt);
		dao.add(generator);

		// Update event collection
		response.sendRedirect("./generators");

	}
}
