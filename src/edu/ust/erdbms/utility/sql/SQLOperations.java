package edu.ust.erdbms.utility.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;



import edu.ust.erdbms.utility.sql.SQLCommands;
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
	
	public static boolean addItem(ProductBean productbean, Connection connection){
		try {
	        PreparedStatement pstmt = connection.prepareStatement(ADD_ITEM);
	        
	        pstmt.setDate(1, productbean.getDate_delivered()); 
	        pstmt.setString(2, productbean.getItem());
	        pstmt.setString(3, productbean.getManufacturer());
	        pstmt.setInt(4, productbean.getProduct_code());
	        pstmt.setInt(5, productbean.getQuantity());
	        pstmt.executeUpdate(); // execute insert statement  
		} catch (SQLException sqle) {
			System.out.println("SQLException - addItem: " + sqle.getMessage());
			return false; 
		}	
		return true;
		
	}
	
	
	public static EmployeeBean searchEmployee(int id, 
		Connection connection) {
		
		EmployeeBean employee = new EmployeeBean();
		 
		try {
	        PreparedStatement pstmt = 
	        	connection.prepareStatement(SEARCH_EMPLOYEE);
	        pstmt.setInt(1, id);             
	        ResultSet rs  = pstmt.executeQuery();
	        
	        while (rs.next()) { 
	        	employee.setLastName(rs.getString("lastname"));
	        	employee.setFirstName(rs.getString("firstname"));
	        	employee.setPosition(rs.getString("position"));
	        	employee.setDepartment(rs.getString("department"));
	        }
		} catch (SQLException sqle) {
			System.out.println("SQLException - searchEmployee: " 
					+ sqle.getMessage());
			return employee; 
		}	
		return employee;
	}
	
	public static ResultSet getAllEmployees(Connection connection) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ALL_EMPLOYEES);  
		} catch (SQLException sqle) {
			System.out.println("SQLException - getALLEmployees: " 
			  + sqle.getMessage());
			return rs; 
		}	
		return rs;
	}
	
	public static int updateEmployee(EmployeeBean employee, 
		int id, Connection connection) {
		int updated = 0;
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = 
	        	connection.prepareStatement(UPDATE_EMPLOYEE);
	        pstmt.setString(1, employee.getLastName()); 
	        pstmt.setString(2, employee.getFirstName());
	        pstmt.setString(3, employee.getPosition());
	        pstmt.setString(4, employee.getDepartment()); 
	        pstmt.setInt(5, id); 
	        updated = pstmt.executeUpdate();   
	        connection.commit();
		} catch (SQLException sqle) {
			System.out.println("SQLException - updateEmployee: " 
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
	
	public static synchronized int deleteEmployee(int id, Connection connection) {
		int updated = 0;
		
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = connection.prepareStatement(DELETE_EMPLOYEE);
	        pstmt.setInt(1, id);             
	        updated  = pstmt.executeUpdate();
	        connection.commit();
		} catch (SQLException sqle) {
			System.out.println("SQLException - deleteEmployee: " + sqle.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException sql) {
				System.err.println("Error on Delete Connection Rollback - " + sql.getMessage());
			}
			return updated; 
		}	
		return updated;
	}
}
