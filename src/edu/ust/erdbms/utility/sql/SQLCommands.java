package edu.ust.erdbms.utility.sql;

public interface SQLCommands {

	String ADD_ITEM= "insert into Product(date_delivered, item, manufacturer, product_code, quantity) values(?,?,?,?,?)";
	String SEARCH_ITEM = "select * from product where product_code=?";
	String DELETE_ITEM = "delete from product where product_code=?";
	String GET_ALL_ITEMS = "select * from product";
	String UPDATE_ITEM = "update product set date_delivered=?, item=?, manufacturer=?, quantity=? where product_code=?";
	String SEARCH_STRING="select * from Product where item like ? "
			+ "OR manufacturer like ? "
			+ "OR quantity like ?  "
			+ " OR date_delivered like ?"
			+ " OR product_code like ?  "; 
	
	String SORT_BY_PRODUCT_CODE = "SELECT * FROM product ORDER BY product_code ASC";
	String SORT_BY_QUANTITY = "SELECT * FROM product ORDER BY quantity DESC";
	String SORT_BY_DATE=  "SELECT * FROM product ORDER BY date_delivered ASC";
}
