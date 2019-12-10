package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private final static String DB_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	private final static String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String DB_USERNAME = "kusuma";
	private final static String DB_PASSWORD = "root";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		
		Class.forName(DB_DRIVER_CLASS);
		con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		System.out.println("DB connection established");
		System.out.println("****************************************************************************************************************");

		return con;
	}
}
