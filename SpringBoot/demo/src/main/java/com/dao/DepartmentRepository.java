package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("from Department where deptName = :dname")
	Department findByName(@Param("dname") String departmentName);
	
}