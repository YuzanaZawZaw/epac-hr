package com.epac.hr.repository;

import com.epac.hr.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    Optional<Absence> findByEmployeeEmployeeIdAndYear(Integer employeeId, Integer year);
    List<Absence> findByEmployeeEmployeeId(Integer employeeId);
    List<Absence> findByYear(Integer year);
}
