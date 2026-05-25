package com.epac.hr.repository;

import com.epac.hr.entity.EmployeeLeaveAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeLeaveAllocationRepository extends JpaRepository<EmployeeLeaveAllocation, Integer> {
    Optional<EmployeeLeaveAllocation> findByEmployeeEmployeeIdAndLeaveTypeLeaveTypeIdAndYear(Integer employeeId, Integer leaveTypeId, Integer year);
    List<EmployeeLeaveAllocation> findByEmployeeEmployeeIdAndYear(Integer employeeId, Integer year);
    List<EmployeeLeaveAllocation> findByYear(Integer year);
}
