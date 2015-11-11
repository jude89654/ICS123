package edu.ust.erdbms.controller;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.model.ProductBean;
import edu.ust.erdbms.utility.sql.SQLOperations;

@WebServlet("/itemupdate.html")
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	public void init() throws ServletException {
		connection = SQLOperations.getConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null; 
		try {
			ProductBean product = new ProductBean();
			
			String date_deliveredString = request.getParameter("date_delivered");
			java.sql.Date date_delivered = new java.sql.Date(new SimpleDateFormat("YYYY-MM-dd").parse(date_deliveredString).getTime());
			
			product.setProduct_code(Integer.parseInt(request.getParameter("product_code")));
			product.setDate_delivered(date_delivered);
			product.setItem(request.getParameter("item"));
			product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			product.setManufacturer(request.getParameter("manufacturer"));
			product.setProduct_price(Double.parseDouble(request.getParameter("product_price")));
			product.compute();
			
			ProductBean bean = SQLOperations.searchProduct(Integer.parseInt(request.getParameter("product_code")), connection);
			if(product.getQuantity()<bean.getQuantity()){
				bean.setQuantity(bean.getQuantity()-product.getQuantity());
				SQLOperations.addSoldProduct(bean, connection);
			}
			request.setAttribute("today",date_delivered);
			int recordsAffected = 
				SQLOperations.updateItem(product, 
						Integer.parseInt(request.getParameter("product_code")), 
						connection);
			request.setAttribute("productrecord", product);
			
			if (recordsAffected > 0) {
				dispatcher = 
			getServletContext().getRequestDispatcher(
					"/viewItem.jsp?status=true");
			} else {
				dispatcher = 
			getServletContext().getRequestDispatcher(
				"/viewItem.jsp?status=false");
			}
			dispatcher.forward(request, response);		
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

}
