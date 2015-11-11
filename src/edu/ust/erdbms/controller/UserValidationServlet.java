package edu.ust.erdbms.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserValidationServlet
 */
@WebServlet("/uservalidation.html")
public class UserValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("username").equals("mattinfantado")
				&&request.getParameter("password").equals("mattinfantado")){
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else
		{
			getServletContext().getRequestDispatcher("/errorlogin.jsp").forward(request, response);
		}
	}

}
