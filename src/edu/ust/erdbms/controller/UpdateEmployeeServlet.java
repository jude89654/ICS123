package edu.ust.erdbms.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.model.EmployeeBean;
import edu.ust.erdbms.utility.sql.SQLOperations;

@WebServlet("/employeeupdate.html")
public class UpdateEmployeeServlet extends HttpServlet {
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
		RequestDispatcher dispatcher = null; 
		try {
			EmployeeBean employee = new EmployeeBean();
			employee.setLastName(request.getParameter("lastName"));
			employee.setFirstName(request.getParameter("firstName"));
			employee.setPosition(request.getParameter("position"));
			employee.setDepartment(request.getParameter("department"));
			
			int recordsAffected = 
				SQLOperations.updateEmployee(employee, 
						Integer.parseInt(request.getParameter("employeeId")), 
						connection);
			request.setAttribute("employee", employee);
			
			if (recordsAffected > 0) {
				dispatcher = 
			getServletContext().getRequestDispatcher(
					"/viewemployee.jsp?success=true");
			} else {
				dispatcher = 
			getServletContext().getRequestDispatcher(
				"/viewemployee.jsp?success=false");
			}
			dispatcher.forward(request, response);		
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

}
