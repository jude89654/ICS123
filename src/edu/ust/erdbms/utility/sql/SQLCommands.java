package edu.ust.erdbms.utility.sql;

public interface SQLCommands {

	String ADD_ITEM= "insert into Product(date_delivered, item, manufacturer, product_code, quantity) values(?,?,?,?,?)";
	String SEARCH_ITEM = "select * from product where product_code=?";
	String DELETE_ITEM = "delete from product where product_code=?";
	String GET_ALL_ITEMS = "select * from product";
	String UPDATE_ITEM = "update product set date_delivered=?, item=?, manufacturer=?, quantity=? where product_code=?";
	
	String INSERT_EMPLOYEE = "insert into employee(lastname, firstname, position, department) values(?,?,?,?)";
	String GET_ALL_EMPLOYEES = "select * from employee";
	String SEARCH_EMPLOYEE = "select * from employee where id=?";
	
	String UPDATE_EMPLOYEE = "update employee set lastname = ?, "
		+ "firstname = ?, position=?, "
		+ "department=? where id = ?";
	
	String DELETE_EMPLOYEE = "delete from employee where id=?";
}
