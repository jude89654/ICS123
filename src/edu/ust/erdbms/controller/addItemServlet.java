package edu.ust.erdbms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ust.erdbms.model.ProductBean;
import edu.ust.erdbms.utility.sql.BeanFactory;
import edu.ust.erdbms.utility.sql.SQLOperations;

@WebServlet("/addItems.html")
public class addItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;

	public void init(/* ServletConfig config */) throws ServletException {
		connection = SQLOperations.getConnection();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String item = request.getParameter("item");
			String manufacturer = request.getParameter("manufacturer");
			int product_code = Integer.parseInt(request
					.getParameter("product_code"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double product_price = Double.parseDouble(request
					.getParameter("product_price"));
			String date_deliveredString = request
					.getParameter("date_delivered");
			java.sql.Date date_delivered = new java.sql.Date(
					new SimpleDateFormat("YYYY-MM-dd").parse(
							date_deliveredString).getTime());

			ProductBean productbean = BeanFactory.getInstance(date_delivered,
					item, manufacturer, product_code, quantity, product_price);

			if (connection != null) {
				if (SQLOperations.addItem(productbean, connection)) {
					System.out.println("item successfully inserted");
					request.setAttribute("productbean", productbean);
					getServletContext().getRequestDispatcher(
							"/additemstatus.jsp?status=true").forward(request,
							response);
				} else {
					getServletContext().getRequestDispatcher(
							"/additemstatus.jsp?status=false").forward(request,
							response);
				}
			} else {
				System.out.print("invalid connection");
			}

		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
