package com.epac.hr.service;

import com.epac.hr.entity.Fortnight;
import com.epac.hr.entity.Fortnight.FortnightStatus;
import com.epac.hr.repository.FortnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class FortnightService {
    
    @Autowired
    private FortnightRepository fortnightRepository;
    
    public Fortnight saveFortnight(Fortnight fortnight) {
        Objects.requireNonNull(fortnight, "Fortnight cannot be null");
        return fortnightRepository.save(fortnight);
    }
    
    public Optional<Fortnight> getFortnightById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return fortnightRepository.findById(id);
    }
    
    public List<Fortnight> getAllFortnights() {
        Objects.requireNonNull(fortnightRepository, "fortnightRepository must not be null");
        return fortnightRepository.findAll();
    }
    
    public Optional<Fortnight> getFortnightByYearAndNumber(Integer year, Integer fortnightNumber) {
        Objects.requireNonNull(year, "year must not be null");
        Objects.requireNonNull(fortnightNumber, "fortnightNumber must not be null");
        return fortnightRepository.findByYearAndFortnightNumber(year, fortnightNumber);
    }
    
    public List<Fortnight> getFortnightsByYear(Integer year) {
        Objects.requireNonNull(year, "year must not be null");
        return fortnightRepository.findByYear(year);
    }
    
    public List<Fortnight> getFortnightsByStatus(FortnightStatus status) {
        Objects.requireNonNull(status, "status must not be null");
        return fortnightRepository.findByStatus(status);
    }
    
    public Optional<Fortnight> getFortnightByDateRange(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        return fortnightRepository.findByStartDateAndEndDate(startDate, endDate);
    }
    
    public Fortnight updateFortnightStatus(Integer id, FortnightStatus status) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(status, "status must not be null");
        Optional<Fortnight> fortnight = fortnightRepository.findById(id);
        if (fortnight.isPresent()) {
            Fortnight f = fortnight.get();
            f.setStatus(status);
            return fortnightRepository.save(f);
        }
        return null;
    }
    
    public void deleteFortnight(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        fortnightRepository.deleteById(id);
    }
}
