package com.epac.hr.service;

import com.epac.hr.entity.NasfundContribution;
import com.epac.hr.repository.NasfundContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NasfundService {
    
    @Autowired
    private NasfundContributionRepository nasfundRepository;
    
    public NasfundContribution saveNasfundContribution(NasfundContribution contribution) {
        Objects.requireNonNull(contribution, "contribution must not be null");
        return nasfundRepository.save(contribution);
    }
    
    public Optional<NasfundContribution> getNasfundById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return nasfundRepository.findById(id);
    }
    
    public List<NasfundContribution> getAllNasfundContributions() {
        return nasfundRepository.findAll();
    }
    
    public List<NasfundContribution> getNasfundByEmployee(Integer employeeId) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        return nasfundRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<NasfundContribution> getNasfundByDateRange(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        return nasfundRepository.findByEmployeeAndDateRange(employeeId, startDate, endDate);
    }
    
    public void deleteNasfund(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        nasfundRepository.deleteById(id);
    }
}
