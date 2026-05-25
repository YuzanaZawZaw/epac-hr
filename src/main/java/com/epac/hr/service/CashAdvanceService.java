package com.epac.hr.service;

import com.epac.hr.entity.CashAdvance;
import com.epac.hr.entity.CashAdvance.ApprovalStatus;
import com.epac.hr.repository.CashAdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CashAdvanceService {
    
    @Autowired
    private CashAdvanceRepository cashAdvanceRepository;
    
    public CashAdvance saveCashAdvance(CashAdvance cashAdvance) {
        return cashAdvanceRepository.save(cashAdvance);
    }
    
    public Optional<CashAdvance> getCashAdvanceById(Integer id) {
        return cashAdvanceRepository.findById(id);
    }
    
    public List<CashAdvance> getAllCashAdvances() {
        return cashAdvanceRepository.findAll();
    }
    
    public List<CashAdvance> getCashAdvancesByEmployee(Integer employeeId) {
        return cashAdvanceRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<CashAdvance> getCashAdvancesByStatus(ApprovalStatus status) {
        return cashAdvanceRepository.findByApprovalStatus(status);
    }
    
    public List<CashAdvance> getCashAdvancesByFortnight(Integer fortnightId) {
        return cashAdvanceRepository.findByFortnightFortnightId(fortnightId);
    }
    
    public CashAdvance approveCashAdvance(Integer id, String approvedBy) {
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
        cashAdvanceRepository.deleteById(id);
    }
}
