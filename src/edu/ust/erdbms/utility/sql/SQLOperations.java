package edu.ust.erdbms.utility.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.sql.Statement;
import java.util.Calendar;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.ust.erdbms.utility.sql.SQLCommands;
import edu.ust.erdbms.model.AccountBean;
import edu.ust.erdbms.model.ProductBean;

public class SQLOperations implements SQLCommands {

	private static Connection connection;
	
	private SQLOperations() {
	}
	
	private static Connection getDBConnection() {
		try {
		    InitialContext context = new InitialContext();
		    DataSource dataSource = (DataSource) 
		     context.lookup("java:comp/env/jdbc/UST-3CSC-DS");
		    
		    if (dataSource != null) {
		    	connection = dataSource.getConnection();
		    }
		} catch (NamingException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return connection;
	 }
	
	public static Connection getConnection() {
		return (connection!=null)?connection:getDBConnection();
	}
	
	public static ResultSet getAllSold(Connection connection) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ALL_SOLD);  
		} catch (SQLException sqle) {
			System.out.println("SQLException - getALLEmployees: " 
			  + sqle.getMessage());
			return rs; 
		}	
		return rs;
	}
	
	public static boolean addSoldProduct(ProductBean productbean, Connection connection){
			try {
		        PreparedStatement pstmt = connection.prepareStatement(ADD_SOLD);
		        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		        pstmt.setString(1, productbean.getItem()); 
		        pstmt.setInt(2, productbean.getProduct_code());
		        pstmt.setInt(3, productbean.getQuantity());
		        pstmt.setDouble(4, productbean.getProduct_price());
		        productbean.compute();
		        pstmt.setDouble(5, productbean.getTotal_price());
		        pstmt.setString(6,productbean.getManufacturer());
		        pstmt.setDate(7, date);
		        pstmt.executeUpdate(); // execute insert statement  
			} catch (SQLException sqle) {
				System.out.println("SQLException - addItem: " + sqle.getMessage());
				return false; 
			}	
			return true;
			
		}
	
	
	public static ResultSet searchProducts(String search,Connection connection,String[] sortby){
		ResultSet rs=null;
		try {
			String query = SEARCH_STRING;
			if(sortby!=null){
			query+=" order by";
			for(int x=0;x<sortby.length;x++){
				if(sortby[x].equals("product_code"))query+=" product_code asc ";
				if(sortby[x].equals("quantity"))query+=" quantity asc ";
				if(sortby[x].equals("manufacturer"))query+=" manufacturer asc ";
				if(sortby[x].equals("item"))query+=" item asc ";
				if(x<sortby.length-1)query+=",";
			}	
			System.out.print(query);
			}
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1,"%"+search+"%");
			pstmt.setString(2,"%"+search+"%");
			pstmt.setString(3,"%"+search+"%");
			pstmt.setString(4,"%"+search+"%");
			pstmt.setString(5,"%"+search+"%");
			
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("Searchproducts error-");
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	
	public static int updateItem(ProductBean product, 
			int id, Connection connection) {
			int updated = 0;
			try {
				connection.setAutoCommit(false);
		        PreparedStatement pstmt = 
		        	connection.prepareStatement(UPDATE_ITEM);
		        pstmt.setDate(1, product.getDate_delivered()); 
		        pstmt.setString(2, product.getItem());
		        pstmt.setString(3, product.getManufacturer());
		        pstmt.setInt(4, product.getQuantity()); 
		        pstmt.setDouble(5, product.getProduct_price()); 
		        pstmt.setDouble(6, product.getTotal_price()); 
		        pstmt.setInt(7, id); 
		        updated = pstmt.executeUpdate();   
		        connection.commit();
			} catch (SQLException sqle) {
				System.out.println("SQLException - updateItem: " 
					+ sqle.getMessage());
				
				try {
					connection.rollback();
				} catch (SQLException sql) {
					System.err.println("Error on Update Connection Rollback - " 
						+ sql.getMessage());
				}
				return updated; 
			}	
			return updated;
		}
	
	public static boolean addItem(ProductBean productbean, Connection connection){
		try {
	        PreparedStatement pstmt = connection.prepareStatement(ADD_ITEM);
	        
	        pstmt.setDate(1, productbean.getDate_delivered()); 
	        pstmt.setString(2, productbean.getItem());
	        pstmt.setString(3, productbean.getManufacturer());
	        pstmt.setInt(4, productbean.getProduct_code());
	        pstmt.setInt(5, productbean.getQuantity());
	        pstmt.setDouble(6,productbean.getProduct_price());
	        pstmt.setDouble(7, productbean.getTotal_price());
	        pstmt.executeUpdate(); // execute insert statement  
		} catch (SQLException sqle) {
			System.out.println("SQLException - addItem: " + sqle.getMessage());
			return false; 
		}	
		return true;
		
	}
	
	public static ResultSet getAllItems(Connection connection) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ALL_ITEMS);  
		} catch (SQLException sqle) {
			System.out.println("SQLException - getALLEmployees: " 
			  + sqle.getMessage());
			return rs; 
		}	
		return rs;
	}
	
	public static synchronized int deleteItem(int id, Connection connection) {
		int updated = 0;
		
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = connection.prepareStatement(DELETE_ITEM);
	        pstmt.setInt(1, id);             
	        updated  = pstmt.executeUpdate();
	        connection.commit();
		} catch (SQLException sqle) {
			System.out.println("SQLException - deleteITEM: " + sqle.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException sql) {
				System.err.println("Error on Delete Connection Rollback - " + sql.getMessage());
			}
			return updated; 
		}	
		return updated;
	}
	
	public static ProductBean searchProduct(int id, 
			Connection connection) {
			
			ProductBean productbean = new ProductBean();
			 
			try {
		        PreparedStatement pstmt = 
		        	connection.prepareStatement(SEARCH_ITEM);
		        pstmt.setInt(1, id);             
		        ResultSet rs  = pstmt.executeQuery();
		        
		        while (rs.next()) { 
		        	productbean.setDate_delivered(rs.getDate("date_delivered"));
		        	productbean.setItem(rs.getString("item"));
		        	productbean.setManufacturer(rs.getString("manufacturer"));
		        	productbean.setProduct_code(id);
		        	productbean.setQuantity(rs.getInt("quantity"));
		        	productbean.setProduct_price(rs.getDouble("product_price"));
		        	productbean.compute();
		        }
			} catch (SQLException sqle) {
				System.out.println("SQLException - searchEmployee: " 
						+ sqle.getMessage());
				return productbean; 
			}	
			return productbean;
		}
	
	public static int getTotalQuantity(Connection connection){
		int total=0;
		
		try {
			PreparedStatement statement= connection.prepareStatement(GET_ALL_ITEMS);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				total+=rs.getInt("quantity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
		
		return total;
	}
	
	public static boolean loginCheck(String username, String password, Connection connection){
	    boolean login = false;
	    ResultSet rs = null;
	    try {
	    	PreparedStatement pstmt =
					connection.prepareStatement(LOGIN_CHECK);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		rs=pstmt.executeQuery();
	    rs = pstmt.getResultSet();
	    login = rs.first(); 
	    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return login;
	}
	
	public static AccountBean searchAdmin(String username,
			Connection connection) {
			
			AccountBean acc= new AccountBean();
			
			try{
				PreparedStatement pstmt =
							connection.prepareStatement(SEARCH_ADMIN);
				pstmt.setString(1, username);
				ResultSet rs=pstmt.executeQuery();
				
				while(rs.next()){
					acc.setUsername(rs.getString("username"));
					acc.setPassword(rs.getString("password"));
					acc.setFirstName(rs.getString("firstName"));
					acc.setLastName(rs.getString("lastName"));
					acc.setEmail(rs.getString("email"));
					}
					
			}
			catch(SQLException sqle){
				System.out.println("SQLException - searchIntern:" 
						+ sqle.getMessage());
				
				return acc;
			}
			return acc;
			
		}
	
}
