package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	String url="jdbc:mysql://localhost:3306/student_db";
	String username="root";
	String password="root"; 
	
	public Connection getConnection() {
		
		Connection con= null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 con= DriverManager.getConnection(url,username,password);
			System.out.println("connection created");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return con;
		
	}

}
