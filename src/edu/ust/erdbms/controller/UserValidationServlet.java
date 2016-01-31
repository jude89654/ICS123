package edu.ust.erdbms.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ust.erdbms.model.AccountBean;
import edu.ust.erdbms.utility.sql.SQLOperations;


@WebServlet("/uservalidation.html")
public class UserValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection;
	
	public void init() throws ServletException {
		connection = SQLOperations.getConnection();
		if (connection != null) {
			getServletContext().setAttribute("dbConnection", connection);
			System.out.println("connection is READY.");
		} else {
			System.err.println("connection is NULL.");
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("username")!=null &&request.getParameter("password")!=null){
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			if(SQLOperations.loginCheck(username, password, connection)){
				System.out.println("successful connection: l");
				
				
				AccountBean admin = 
				  SQLOperations.searchAdmin(username, connection);
				
				HttpSession session=request.getSession();
				session.setAttribute("admin", admin);
				request.setAttribute("Admin", admin);
				
				getServletContext().getRequestDispatcher("/index.jsp")
				.forward(request, response);
			}
			
		}
		else
		{
			getServletContext().getRequestDispatcher("/errorlogin.jsp").forward(request, response);
		}
	}

}
