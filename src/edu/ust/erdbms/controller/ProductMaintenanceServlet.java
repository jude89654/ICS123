package edu.ust.erdbms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.model.ProductBean;
import edu.ust.erdbms.utility.sql.SQLOperations;

/**
 * Servlet implementation class ProductMaintenanceServlet
 */
@WebServlet("/productmaintenance.html")
public class ProductMaintenanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
Connection connection;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(/*ServletConfig config*/) throws ServletException {
		connection=SQLOperations.getConnection();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher dispatcher = null; 
			if (request.getParameter("action").equals("edit")) {
				int id = Integer.parseInt(request.getParameter("product_code"));
				ProductBean product = 
				  SQLOperations.searchProduct(id, connection);
				request.setAttribute("productrecord", product);
				request.setAttribute("today",product.getDate_delivered());
				request.setAttribute("product_code",id);
				dispatcher = 
				 getServletContext().getRequestDispatcher("/editItem.jsp");;
			} else if (request.getParameter("action").equals("delete")){
				int id = Integer.parseInt(request.getParameter("product_code"));
				SQLOperations.addSoldProduct(SQLOperations.searchProduct(id, connection), connection);
				SQLOperations.deleteItem(id, connection);
				ResultSet rs = 
						  SQLOperations.getAllItems(connection);
				request.setAttribute("productrecords", rs);
				dispatcher = 
				 getServletContext().getRequestDispatcher("/listItems.jsp");	
			}
			dispatcher.forward(request, response);		
		} catch (Exception e) {
			System.err.println("Exception e - " + e.getMessage());
			e.printStackTrace();
		} 
	}

}
