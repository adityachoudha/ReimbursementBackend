package com.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.model.EmployeeOtp;

@Service
public class EmployeeDAO {
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeOtp empOtp;
	@Autowired
	JavaMailSender mailSender;
	
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(int empId) {
		return employeeRepository.findById(empId).orElse(new Employee(0, "Not Found", 0.0, "", "", "", "", null));
	}

	public Employee getEmployeeByName(String empName) {
		return employeeRepository.findByName(empName);
	}
	
	public void registerEmployee(Employee emp) {
		BCryptPasswordEncoder  enp = new BCryptPasswordEncoder() ;
		String bcryptpw = enp.encode(emp.getPassword());
		emp.setPassword(bcryptpw);
		employeeRepository.save(emp);
	}
	
	public void updateEmployee(Employee emp) {
		employeeRepository.save(emp);
	}
	
	public void deleteEmployeeById(int empId) {
		employeeRepository.deleteById(empId);
	}	
	public Employee empLogin(String emailId, String password) {
		  return employeeRepository.empLogin(emailId, password);
		}

	public Employee getEmployeByEmailId(String emailId) {
		return employeeRepository.findByEmail(emailId);
	}

	public boolean EmailOtp(Employee employee) {
		Employee emp = employeeRepository.findByEmail(employee.getEmailId());
		System.out.println("Test " + emp);
		if(emp != null){
			int otp = (int) (Math.random() * 900000)+100000;
			LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(3);
			empOtp.setEmailOtp(otp);
			empOtp.setEmailOtpExpiryTime(expiryTime);
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(emp.getEmailId());
			msg.setSubject("Your OTP code");
			msg.setText("Your OTP code is "+otp+ "\n\nFrom Reimbursement");
			mailSender.send(msg);
			return true;
		}
		return false;
	}

	public boolean validateEmailOtp(String emailId, int otp) {
		Employee emp = employeeRepository.findByEmail(emailId);
		if(emp != null){
			if(empOtp.getEmailOtp() == otp && empOtp.getEmailOtpExpiryTime().isAfter(LocalDateTime.now())){
				empOtp.setEmailOtp(0);
				empOtp.setEmailOtpExpiryTime(null);
				return true;
			}
		}
		return false;
	}	
}