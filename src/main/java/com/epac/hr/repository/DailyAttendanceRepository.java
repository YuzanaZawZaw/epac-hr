package com.epac.hr.repository;

import com.epac.hr.entity.DailyAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, Integer> {
    Optional<DailyAttendance> findByEmployeeEmployeeIdAndAttendanceDate(Integer employeeId, LocalDate attendanceDate);
    List<DailyAttendance> findByEmployeeEmployeeIdAndFortnightFortnightId(Integer employeeId, Integer fortnightId);
    
    @Query("SELECT da FROM DailyAttendance da WHERE da.employee.employeeId = :employeeId AND da.attendanceDate BETWEEN :startDate AND :endDate")
    List<DailyAttendance> findByEmployeeAndDateRange(@Param("employeeId") Integer employeeId, 
                                                      @Param("startDate") LocalDate startDate, 
                                                      @Param("endDate") LocalDate endDate);
}
