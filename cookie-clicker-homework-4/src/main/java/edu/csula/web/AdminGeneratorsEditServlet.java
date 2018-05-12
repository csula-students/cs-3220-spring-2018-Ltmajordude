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

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.mysql.Database;

@WebServlet("/admin/generators/edit")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminGeneratorsEditServlet extends HttpServlet {
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());

		request.setAttribute("entry", dao.getById(id));
        request.getRequestDispatcher("/WEB-INF/admin-generators-edit.jsp").forward(request, response);
		
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlockAt"));

		int id = Integer.parseInt(request.getParameter("id"));

		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());

		Generator generator = new Generator(dao.getAll().size(), name, description, rate, baseCost, unlockAt);
		
		dao.set(id, generator);

		response.sendRedirect("../generators");

	}
}
