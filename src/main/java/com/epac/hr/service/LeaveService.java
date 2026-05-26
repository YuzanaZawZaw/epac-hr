package com.epac.hr.service;

import com.epac.hr.entity.LeaveRequest;
import com.epac.hr.entity.LeaveRequest.ApprovalStatus;
import com.epac.hr.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
        Objects.requireNonNull(leaveRequest, "LeaveRequest cannot be null");
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public Optional<LeaveRequest> getLeaveRequestById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return leaveRequestRepository.findById(id);
    }
    
    public List<LeaveRequest> getAllLeaveRequests() {
        Objects.requireNonNull(leaveRequestRepository, "leaveRequestRepository must not be null");
        return leaveRequestRepository.findAll();
    }
    
    public List<LeaveRequest> getLeaveRequestsByEmployee(Integer employeeId) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        return leaveRequestRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<LeaveRequest> getLeaveRequestsByStatus(ApprovalStatus status) {
        Objects.requireNonNull(status, "status must not be null");
        return leaveRequestRepository.findByApprovalStatus(status);
    }
    
    public List<LeaveRequest> getApprovedLeavesByEmployee(Integer employeeId) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        return leaveRequestRepository.findApprovedLeavesByEmployee(employeeId);
    }
    
    public List<LeaveRequest> getLeavesByDateRange(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        return leaveRequestRepository.findLeavesByDateRange(startDate, endDate);
    }
    
    public LeaveRequest approveLeaveRequest(Integer id, String approvedBy) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(approvedBy, "approvedBy must not be null");
        Optional<LeaveRequest> leaveRequest = leaveRequestRepository.findById(id);
        if (leaveRequest.isPresent()) {
            LeaveRequest lr = leaveRequest.get();
            lr.setApprovalStatus(ApprovalStatus.APPROVED);
            lr.setApprovedBy(approvedBy);
            lr.setApprovalDate(LocalDate.now());
            return leaveRequestRepository.save(lr);
        }
        return null;
    }
    
    public LeaveRequest rejectLeaveRequest(Integer id, String approvedBy) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(approvedBy, "approvedBy must not be null");
        Optional<LeaveRequest> leaveRequest = leaveRequestRepository.findById(id);
        if (leaveRequest.isPresent()) {
            LeaveRequest lr = leaveRequest.get();
            lr.setApprovalStatus(ApprovalStatus.REJECTED);
            lr.setApprovedBy(approvedBy);
            lr.setApprovalDate(LocalDate.now());
            return leaveRequestRepository.save(lr);
        }
        return null;
    }
    
    public void deleteLeaveRequest(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        leaveRequestRepository.deleteById(id);
    }
}
