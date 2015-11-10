package edu.ust.erdbms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.model.EmployeeBean;
import edu.ust.erdbms.utility.sql.SQLOperations;

import java.sql.*;

@WebServlet("/employeemaintenance.html")
public class EmployeeMaintenanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection;
	
	public void init() throws ServletException {
		connection = (Connection) 
			getServletContext().getAttribute("dbConnection");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher dispatcher = null; 
			if (request.getParameter("action").equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				EmployeeBean employee = 
				  SQLOperations.searchEmployee(id, connection);
				request.setAttribute("employeeRec", employee);
				dispatcher = 
				 getServletContext().getRequestDispatcher("/editemployee.jsp");
			} else if (request.getParameter("action").equals("delete")){
				int id = Integer.parseInt(request.getParameter("id"));
				
				SQLOperations.deleteEmployee(id, connection);
				ResultSet rs = 
						  SQLOperations.getAllEmployees(connection);
				request.setAttribute("recordEmployees", rs);
				dispatcher = 
				 getServletContext().getRequestDispatcher("/listemployees.jsp");	
			}
			dispatcher.forward(request, response);		
		} catch (Exception e) {
			System.err.println("Exception e - " + e.getMessage());
			e.printStackTrace();
		} 
	}

}
