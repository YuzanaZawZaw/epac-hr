package com.epac.hr.repository;

import com.epac.hr.entity.CashAdvance;
import com.epac.hr.entity.CashAdvance.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CashAdvanceRepository extends JpaRepository<CashAdvance, Integer> {
    List<CashAdvance> findByEmployeeEmployeeId(Integer employeeId);
    List<CashAdvance> findByApprovalStatus(ApprovalStatus status);
    List<CashAdvance> findByFortnightFortnightId(Integer fortnightId);
}
