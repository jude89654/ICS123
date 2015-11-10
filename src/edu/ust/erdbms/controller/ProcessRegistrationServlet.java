package edu.ust.erdbms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.model.EmployeeBean;
import edu.ust.erdbms.utility.BeanFactory;
import edu.ust.erdbms.utility.sql.SQLOperations;

import java.sql.*;

@WebServlet("/processregistration.html")
public class ProcessRegistrationServlet extends HttpServlet {
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
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String position = request.getParameter("position");
		String department = request.getParameter("department");
		
		EmployeeBean employee = 
				BeanFactory.getFactoryBean(lastName, firstName, position, department);
		
		if (connection != null) {
			if (SQLOperations.addEmployee(employee, connection)){
				System.out.println("successful insert");
				request.setAttribute("employee", employee);
				getServletContext().getRequestDispatcher("/actionstatus.jsp?success=true").forward(request, response);
			} else {
				System.out.println("failed insert");
				getServletContext().getRequestDispatcher("/actionstatus.jsp?success=false").forward(request, response);
			}
		} else {
			System.out.println("invalid connection");
		}
	}

}
