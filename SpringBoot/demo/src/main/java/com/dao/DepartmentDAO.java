package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Department;

@Service
public class DepartmentDAO {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}
	
	public Department getDepartmentById(int deptId) {
		return departmentRepository.findById(deptId).orElse(new Department(0, "Not Found!", ""));
	}
	
	public Department getDepartmentByName(String deptName) {
		return departmentRepository.findByName(deptName);
	}
	
	public void registerDepartment(Department dept) {
		departmentRepository.save(dept);
	}
	
	public void updateDepartment(Department dept) {
		departmentRepository.save(dept);
	}
	
	public void deleteDepartmentById(int deptId) {
		departmentRepository.deleteById(deptId);
	}

}