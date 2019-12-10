package com.jdbc.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.oracore.OracleType;
// select details of admin VP of Executive dept 
public class EmployeeJDBCCall {
	
	private static final String SELECT_QUERY = "select e.first_name||' '||e.last_name as Name,m.first_name||' '||m.last_name as manager_name,e.phone_number,e.salary\r\n" + 
			"from employees e, departments, jobs,employees m\r\n" + 
			"where e.department_id = departments.department_id\r\n" + 
			"and e.job_id= jobs.job_id\r\n" + 
			"and m.employee_id = e.manager_id\r\n" + 
			"and departments.department_name = ? \r\n" + 
			"and jobs.job_title = ? \r\n";
	
	public static void main(String[] args) throws Exception {
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			List<EmpDetails> empDetailsList = getPrepareResult(con,"Executive","Administration Vice President");
			for(EmpDetails empDetails : empDetailsList) {
				System.out.println(empDetails);
			}
			System.out.println("****************************************************************************************************************");
			System.out.println("****************************************************************************************************************");
			List<EmpDetails> empDetailsList1 = getCallableResult(con,"Executive","Administration Vice President");
			for(EmpDetails empDetails : empDetailsList1) {
				System.out.println(empDetails);
			}
		
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
	
	public static List<EmpDetails> getPrepareResult(Connection con, String departmentName, String jobName) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
		ps.setString(1, departmentName);
		ps.setString(2, jobName);
		List<EmpDetails> al = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String name = rs.getString(1);
			String managerName = rs.getString(2);
			String phoneNum = rs.getString(3);
			Integer sal = rs.getInt(4);
			
			EmpDetails emp = new EmpDetails();
			emp.setManagerName(managerName);
			emp.setName(name);
			emp.setPhoneNumber(phoneNum);
			emp.setSalary(sal);
			
			al.add(emp);
		}
		return al;
		
	}
	
	public static List<EmpDetails> getCallableResult(Connection con, String departmentName, String jobName) throws SQLException {
				
		CallableStatement cs = con.prepareCall("{call get_emp_details_bind(?,?,?)}");
	//	CallableStatement cs = con.prepareCall("{call get_emp_details(?,?,?)}");
		List<EmpDetails> al = new ArrayList<>();
		cs.setString(1, departmentName);
		cs.setString(2, jobName);
		cs.registerOutParameter(3, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(3);
		while(rs.next()) {
			String name = rs.getString(1);
			String managerName = rs.getString(2);
			String phoneNum = rs.getString(3);
			Integer sal = rs.getInt(4);
			
			EmpDetails emp = new EmpDetails();
			emp.setManagerName(managerName);
			emp.setName(name);
			emp.setPhoneNumber(phoneNum);
			emp.setSalary(sal);
			
			al.add(emp);
		}
		return al;
		
	}

}

