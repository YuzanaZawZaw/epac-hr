package com.epac.hr.repository;

import com.epac.hr.entity.Fortnight;
import com.epac.hr.entity.Fortnight.FortnightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FortnightRepository extends JpaRepository<Fortnight, Integer> {
    Optional<Fortnight> findByYearAndFortnightNumber(Integer year, Integer fortnightNumber);
    List<Fortnight> findByYear(Integer year);
    List<Fortnight> findByStatus(FortnightStatus status);
    Optional<Fortnight> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}
