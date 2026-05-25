package com.epac.hr.repository;

import com.epac.hr.entity.PayrollDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PayrollDetailRepository extends JpaRepository<PayrollDetail, Integer> {
    List<PayrollDetail> findByPayrollFortnightPayrollId(Integer payrollId);
}
