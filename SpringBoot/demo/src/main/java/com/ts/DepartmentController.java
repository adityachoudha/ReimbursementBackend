package com.ts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DepartmentDAO;
import com.model.Department;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentDAO departmentDao;
	
	@GetMapping("getDepartments")
	public List<Department> getDepartments() {
		return departmentDao.getDepartments();
	}
	
	@GetMapping("getDepartmentById/{deptId}")
	public Department getDepartmentById(@PathVariable("deptId") int departmentId) {
		return departmentDao.getDepartmentById(departmentId);
	}
	
	@GetMapping("getDepartmentByName/{deptName}")
	public Department getDepartmentByName(@PathVariable("deptName") String departmentName) {
		return departmentDao.getDepartmentByName(departmentName);
	}
	
	@PostMapping("registerDepartment")
	public String registerDepartment(@RequestBody Department department) {
		departmentDao.registerDepartment(department);
		return "Department Registered Successfully!!!";
	}
	
	@PutMapping("updateDepartment")
	public String updateDepartment(@RequestBody Department department) {
		departmentDao.updateDepartment(department);
		return "Department Updated Successfully!!!";
	}
	
	@DeleteMapping("deleteDepartmentById/{deptId}")
	public String deleteDepartmentById(@PathVariable("deptId") int departmentId) {
		departmentDao.deleteDepartmentById(departmentId);
		return "Department Deleted Successfully!!!";
	}
	
}
