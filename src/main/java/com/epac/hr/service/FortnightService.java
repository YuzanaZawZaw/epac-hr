package com.epac.hr.service;

import com.epac.hr.entity.Fortnight;
import com.epac.hr.entity.Fortnight.FortnightStatus;
import com.epac.hr.repository.FortnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FortnightService {
    
    @Autowired
    private FortnightRepository fortnightRepository;
    
    public Fortnight saveFortnight(Fortnight fortnight) {
        return fortnightRepository.save(fortnight);
    }
    
    public Optional<Fortnight> getFortnightById(Integer id) {
        return fortnightRepository.findById(id);
    }
    
    public List<Fortnight> getAllFortnights() {
        return fortnightRepository.findAll();
    }
    
    public Optional<Fortnight> getFortnightByYearAndNumber(Integer year, Integer fortnightNumber) {
        return fortnightRepository.findByYearAndFortnightNumber(year, fortnightNumber);
    }
    
    public List<Fortnight> getFortnightsByYear(Integer year) {
        return fortnightRepository.findByYear(year);
    }
    
    public List<Fortnight> getFortnightsByStatus(FortnightStatus status) {
        return fortnightRepository.findByStatus(status);
    }
    
    public Optional<Fortnight> getFortnightByDateRange(LocalDate startDate, LocalDate endDate) {
        return fortnightRepository.findByStartDateAndEndDate(startDate, endDate);
    }
    
    public Fortnight updateFortnightStatus(Integer id, FortnightStatus status) {
        Optional<Fortnight> fortnight = fortnightRepository.findById(id);
        if (fortnight.isPresent()) {
            Fortnight f = fortnight.get();
            f.setStatus(status);
            return fortnightRepository.save(f);
        }
        return null;
    }
    
    public void deleteFortnight(Integer id) {
        fortnightRepository.deleteById(id);
    }
}
