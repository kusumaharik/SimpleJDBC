package com.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeJDBCInsertExample {
	
	private static final String INSERT_ADDRESS = "insert into Address(empId,address,city,country) values(?,?,?,?)";
	
	public static void main(String[] args) throws Exception {
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);			
			insertAddressData(con, 7, "ABC", "PUNE", "India");
			int a = 5/0;
			insertAddressData(con, 8, "ABC", "PUNE", "India");
			con.commit();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void insertAddressData(Connection con, int id, String address, String city, String country) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement(INSERT_ADDRESS);
		ps.setInt(1, id);
		ps.setString(2, address);
		ps.setString(3, city);
		ps.setString(4, country);
		ps.execute();
		System.out.println(" rows successfully updated");
		ps.close();
	}

}

