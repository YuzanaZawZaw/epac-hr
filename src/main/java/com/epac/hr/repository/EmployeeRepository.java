package com.epac.hr.repository;

import com.epac.hr.entity.Employee;
import com.epac.hr.entity.Employee.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmployeeCode(String employeeCode);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByNasfundNumber(String nasfundNumber);
    List<Employee> findByEmployeeStatus(EmployeeStatus status);
    List<Employee> findByDepartmentDepartmentId(Integer departmentId);
    
    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE %:name%")
    List<Employee> searchByFullName(@Param("name") String name);
    
    @Query("SELECT e FROM Employee e WHERE e.employeeStatus = :status AND e.department.departmentId = :departmentId")
    List<Employee> findByStatusAndDepartment(@Param("status") EmployeeStatus status, @Param("departmentId") Integer departmentId);
}
