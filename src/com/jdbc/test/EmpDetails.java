package com.jdbc.test;

public class EmpDetails {
	
	private String name;
	
	private String managerName;
	
	private String phoneNumber;
	
	private int salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmpDetails [name=" + name + ", managerName=" + managerName + ", phoneNumber=" + phoneNumber
				+ ", salary=" + salary + "]";
	}
	
	

}
