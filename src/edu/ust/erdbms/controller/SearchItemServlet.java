package edu.ust.erdbms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.utility.sql.SQLOperations;

/**
 * Servlet implementation class SearchItemServlet
 */
@WebServlet("/searchitem.html")
public class SearchItemServlet extends HttpServlet {
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
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			if (connection != null) {
				
				ResultSet rs = SQLOperations.searchProducts(request.getParameter("query"),connection,request.getParameterValues("sortby")); 			
				request.setAttribute("productrecords", rs);
				getServletContext().getRequestDispatcher("/searchresults.jsp")
					.forward(request, response);
			} else {
				System.out.println("Invalid Connection resource");
			}
		 } catch (NullPointerException npe) {
				System.err.println("Invalid Connection resource - " + npe.getMessage());
		 } catch (Exception e) {
				System.err.println("Exception - " + e.getMessage());
		 } 
	}

}
