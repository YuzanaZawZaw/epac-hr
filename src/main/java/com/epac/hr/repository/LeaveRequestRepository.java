package com.epac.hr.repository;

import com.epac.hr.entity.LeaveRequest;
import com.epac.hr.entity.LeaveRequest.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByEmployeeEmployeeId(Integer employeeId);
    List<LeaveRequest> findByApprovalStatus(ApprovalStatus status);
    
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee.employeeId = :employeeId AND lr.approvalStatus = 'APPROVED'")
    List<LeaveRequest> findApprovedLeavesByEmployee(@Param("employeeId") Integer employeeId);
    
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.leaveStartDate <= :endDate AND lr.leaveEndDate >= :startDate")
    List<LeaveRequest> findLeavesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
