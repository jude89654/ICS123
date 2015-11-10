package edu.ust.erdbms.utility.sql;

public interface SQLCommands {

	String ADD_ITEM= "insert into Product(date_delivered, item, manufacturer, product_code, quantity) values(?,?,?,?,?)";
	String INSERT_EMPLOYEE = "insert into employee(lastname, firstname, position, department) values(?,?,?,?)";
	String GET_ALL_EMPLOYEES = "select * from employee";
	String SEARCH_EMPLOYEE = "select * from employee where id=?";
	String UPDATE_EMPLOYEE = "update employee set lastname = ?, "
		+ "firstname = ?, position=?, "
		+ "department=? where id = ?";
	
	String DELETE_EMPLOYEE = "delete from employee where id=?";
}
