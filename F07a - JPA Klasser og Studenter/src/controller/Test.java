package controller;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;

public class Test {

	
	
	
	public static void main(String[] args) {
		Connection conn = null;

		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/skole?" +
		                                   "user=test&password=test");

		    Statement stmt = null;
		    ResultSet rs = null;

 		        stmt = conn.createStatement();
		
 		        rs = stmt.executeQuery("SELECT * FROM student");

		       

		        while (rs.next()) {
		            String id = rs.getString("id");
		            String nv = rs.getString("navn");
		            System.out.println("Id: " + id + ", navn: " + nv);
		        }

		        // Now do something with the ResultSet ....
		    

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		System.out.println("ok");
	}

}
