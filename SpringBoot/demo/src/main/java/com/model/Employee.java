package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {

	@Id@GeneratedValue
	private int empId;
	private String empName;
	private double salary;
	private String gender;
	private String country;
	private String DOJ;
	
	private String emailId;
	private String password;
	
	@ManyToOne
	@JoinColumn(name="deptId")
	Department department;

	public Employee() {
		super();
	}

	public Employee(int empId, String empName, double salary, String gender, String country, String emailId,
			String password, Department department) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
		this.gender = gender;
		this.country = country;
		this.emailId = emailId;
		this.password = password;
		this.department = department;
	}
	
	
	
	public String getDOJ() {
		return DOJ;
	}

	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}

	
	

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + ", gender=" + gender
				+ ", country=" + country + ", emailId=" + emailId + ", password=" + password + ", department="
				+ department + "]";
	}	
}
