package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("from Employee e where e.empName = :ename")
	Employee findByName(@Param("ename") String employeeName);

	@Query("from Employee e where e.emailId = :emailId and e.password = :password")
	Employee empLogin(@Param("emailId") String emailId, @Param("password") String password);

	@Query("from Employee e where e.emailId=:emailId")
	Employee findByEmail(@Param("emailId") String emailId);
}
