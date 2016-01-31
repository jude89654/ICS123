package edu.ust.erdbms.utility.sql;

public interface SQLCommands {

	String ADD_ITEM= "insert into Product(date_delivered, item, manufacturer, product_code, quantity, product_price, total_price) values(?,?,?,?,?,?,?)";
	String SEARCH_ITEM = "select * from product where product_code=?";
	String DELETE_ITEM = "delete from product where product_code=?";
	String GET_ALL_ITEMS = "select * from product";
	String UPDATE_ITEM = "update product set date_delivered=?, item=?, manufacturer=?,"
			+ " quantity=?, product_price=?, total_price=? where product_code=?";
	String SEARCH_STRING="select * from Product where item like ? "
			+ "OR manufacturer like ? "
			+ "OR quantity like ?  "
			+ " OR date_delivered like ?"
			+ " OR product_code like ?  "; 
	String ADD_SOLD = "insert into sold(item, product_code, quantity,product_price, total_price, manufacturer, date_sold) values(?,?,?,?,?,?,?)";
	String GET_ALL_SOLD = "select * from sold";
	
	/*LOGIN COMMANDS*/
	String LOGIN_CHECK="select username, password, email from accounts where username=? and password=?";
	String SEARCH_ADMIN="select * from accounts where username=?";

}
