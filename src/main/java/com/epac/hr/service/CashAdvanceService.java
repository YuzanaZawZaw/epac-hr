package com.epac.hr.service;

import com.epac.hr.entity.CashAdvance;
import com.epac.hr.entity.CashAdvance.ApprovalStatus;
import com.epac.hr.repository.CashAdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class CashAdvanceService {
    
    @Autowired
    private CashAdvanceRepository cashAdvanceRepository;
    
    public CashAdvance saveCashAdvance(CashAdvance cashAdvance) {
        Objects.requireNonNull(cashAdvance, "CashAdvance cannot be null");
        return cashAdvanceRepository.save(cashAdvance);
    }
    
    public Optional<CashAdvance> getCashAdvanceById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return cashAdvanceRepository.findById(id);
    }
    
    public List<CashAdvance> getAllCashAdvances() {
        Objects.requireNonNull(cashAdvanceRepository, "cashAdvanceRepository must not be null");
        return cashAdvanceRepository.findAll();
    }
    
    public List<CashAdvance> getCashAdvancesByEmployee(Integer employeeId) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        return cashAdvanceRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<CashAdvance> getCashAdvancesByStatus(ApprovalStatus status) {
        Objects.requireNonNull(status, "status must not be null");
        return cashAdvanceRepository.findByApprovalStatus(status);
    }
    
    public List<CashAdvance> getCashAdvancesByFortnight(Integer fortnightId) {
        Objects.requireNonNull(fortnightId, "fortnightId must not be null");
        return cashAdvanceRepository.findByFortnightFortnightId(fortnightId);
    }
    
    public CashAdvance approveCashAdvance(Integer id, String approvedBy) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(approvedBy, "approvedBy must not be null");
        Optional<CashAdvance> cashAdvance = cashAdvanceRepository.findById(id);
        if (cashAdvance.isPresent()) {
            CashAdvance ca = cashAdvance.get();
            ca.setApprovalStatus(ApprovalStatus.APPROVED);
            ca.setApprovedBy(approvedBy);
            ca.setApprovalDate(java.time.LocalDate.now());
            return cashAdvanceRepository.save(ca);
        }
        return null;
    }
    
    public void deleteCashAdvance(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        cashAdvanceRepository.deleteById(id);
    }
}
