package com.ts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeDAO;
import com.dao.EmployeeRepository;

import com.model.Employee;


@RestController
public class EmployeeController {
		
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeDAO employeeDao;
	
	@GetMapping("getEmployees")
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees();
	}
	
	@GetMapping("getEmployeeById/{empId}")
	public Employee getEmployeeById(@PathVariable("empId") int employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}
	
	@GetMapping("getEmployeeByName/{empName}")
	public Employee getEmployeeByName(@PathVariable("empName") String employeeName) {
		return employeeDao.getEmployeeByName(employeeName);
	}
	
	@PostMapping("registerEmployee")
	public String registerEmployee(@RequestBody Employee employee) {
		employeeDao.registerEmployee(employee);
		return "Employee Registered Successfully!!!";
	}
	
	@PutMapping("updateEmployee")
	public String updateEmployee(@RequestBody Employee employee) {
		employeeDao.updateEmployee(employee);
		return "Employee Updated Successfully!!!";
	}
	
	@DeleteMapping("deleteEmployeeById/{empId}")
	public String deleteEmployeeById(@PathVariable("empId") int employeeId) {
		employeeDao.deleteEmployeeById(employeeId);
		return "Employee Deleted Successfully!!!";
	}
	@GetMapping("empLogin/{emailId}/{password}")
	public Employee empLogin(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {
		Employee employee=employeeDao.getEmployeByEmailId(emailId);
		if(employee != null){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(password, employee.getPassword())){
				return employee;
			}
		}else{
			return null;
		}
	  return employeeDao.empLogin(emailId,password);
	}
	
	@PostMapping("emailotp")
	public ResponseEntity<Map<String, String>> EmailOtp(@RequestBody Employee employee){
		Map<String, String> response = new HashMap<>();
		if(employeeDao.EmailOtp(employee)){
			response.put("message", "OTP is sent");
		}else{
			System.out.print(employee);
			response.put("message", "Invalid Email");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("validateEmailOtp/{emailId}/{otp}")
	public ResponseEntity<Map<String,String>> validateEmailOtp(@PathVariable("emailId") String emailId, @PathVariable("otp") int otp){
		Map<String, String> response = new HashMap<>();
		if(employeeDao.validateEmailOtp(emailId, otp)){
			response.put("message", "OTP");
		}else{
			response.put("message", "Invalid OTP");
		}
		return ResponseEntity.ok(response);
	}


}