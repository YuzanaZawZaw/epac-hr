package com.epac.hr.repository;

import com.epac.hr.entity.NasfundContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface NasfundContributionRepository extends JpaRepository<NasfundContribution, Integer> {
    List<NasfundContribution> findByEmployeeEmployeeId(Integer employeeId);
    
    @Query("SELECT nc FROM NasfundContribution nc WHERE nc.employee.employeeId = :employeeId AND nc.contributionDate BETWEEN :startDate AND :endDate")
    List<NasfundContribution> findByEmployeeAndDateRange(@Param("employeeId") Integer employeeId, 
                                                          @Param("startDate") LocalDate startDate, 
                                                          @Param("endDate") LocalDate endDate);
}
