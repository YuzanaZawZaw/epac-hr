package com.epac.hr.repository;

import com.epac.hr.entity.EmployeeEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeEmploymentRepository extends JpaRepository<EmployeeEmployment, Integer> {
    // Find employments by employee PK
    List<EmployeeEmployment> findByEmployeeEmployeeId(Integer employeeId);

    // Convenience: find by employee code
    List<EmployeeEmployment> findByEmployeeEmployeeCode(String employeeCode);
}
