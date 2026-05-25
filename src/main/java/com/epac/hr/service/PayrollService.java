package com.epac.hr.service;

import com.epac.hr.entity.PayrollFortnight;
import com.epac.hr.entity.PayrollFortnight.PayrollStatus;
import com.epac.hr.repository.PayrollFortnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {
    
    @Autowired
    private PayrollFortnightRepository payrollRepository;
    
    public PayrollFortnight savePayroll(PayrollFortnight payroll) {
        return payrollRepository.save(payroll);
    }
    
    public Optional<PayrollFortnight> getPayrollById(Integer id) {
        return payrollRepository.findById(id);
    }
    
    public List<PayrollFortnight> getAllPayrolls() {
        return payrollRepository.findAll();
    }
    
    public Optional<PayrollFortnight> getPayrollByEmployeeAndFortnight(Integer employeeId, Integer fortnightId) {
        return payrollRepository.findByEmployeeEmployeeIdAndFortnightFortnightId(employeeId, fortnightId);
    }
    
    public List<PayrollFortnight> getPayrollByEmployee(Integer employeeId) {
        return payrollRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<PayrollFortnight> getPayrollByStatus(PayrollStatus status) {
        return payrollRepository.findByPayrollStatus(status);
    }
    
    public List<PayrollFortnight> getPayrollByFortnight(Integer fortnightId) {
        return payrollRepository.findByFortnightFortnightId(fortnightId);
    }
    
    public PayrollFortnight updatePayrollStatus(Integer id, PayrollStatus status) {
        Optional<PayrollFortnight> payroll = payrollRepository.findById(id);
        if (payroll.isPresent()) {
            PayrollFortnight p = payroll.get();
            p.setPayrollStatus(status);
            return payrollRepository.save(p);
        }
        return null;
    }
    
    public PayrollFortnight approvePayroll(Integer id, String approvedBy) {
        Optional<PayrollFortnight> payroll = payrollRepository.findById(id);
        if (payroll.isPresent()) {
            PayrollFortnight p = payroll.get();
            p.setPayrollStatus(PayrollStatus.APPROVED);
            p.setApprovedBy(approvedBy);
            p.setApprovedDate(java.time.LocalDateTime.now());
            return payrollRepository.save(p);
        }
        return null;
    }
    
    public void deletePayroll(Integer id) {
        payrollRepository.deleteById(id);
    }
}
