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

@WebServlet("/admin/generators/delete")
//@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminGeneratorsDeleteServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		int id = Integer.parseInt(request.getParameter("id"));
		dao.remove(id);

		response.sendRedirect("../generators");

	}

}
