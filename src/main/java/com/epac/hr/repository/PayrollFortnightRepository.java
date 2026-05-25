package com.epac.hr.repository;

import com.epac.hr.entity.PayrollFortnight;
import com.epac.hr.entity.PayrollFortnight.PayrollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollFortnightRepository extends JpaRepository<PayrollFortnight, Integer> {
    Optional<PayrollFortnight> findByEmployeeEmployeeIdAndFortnightFortnightId(Integer employeeId, Integer fortnightId);
    List<PayrollFortnight> findByEmployeeEmployeeId(Integer employeeId);
    List<PayrollFortnight> findByPayrollStatus(PayrollStatus status);
    List<PayrollFortnight> findByFortnightFortnightId(Integer fortnightId);
}
