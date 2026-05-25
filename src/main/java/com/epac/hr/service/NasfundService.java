package com.epac.hr.service;

import com.epac.hr.entity.NasfundContribution;
import com.epac.hr.repository.NasfundContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NasfundService {
    
    @Autowired
    private NasfundContributionRepository nasfundRepository;
    
    public NasfundContribution saveNasfundContribution(NasfundContribution contribution) {
        return nasfundRepository.save(contribution);
    }
    
    public Optional<NasfundContribution> getNasfundById(Integer id) {
        return nasfundRepository.findById(id);
    }
    
    public List<NasfundContribution> getAllNasfundContributions() {
        return nasfundRepository.findAll();
    }
    
    public List<NasfundContribution> getNasfundByEmployee(Integer employeeId) {
        return nasfundRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<NasfundContribution> getNasfundByDateRange(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        return nasfundRepository.findByEmployeeAndDateRange(employeeId, startDate, endDate);
    }
    
    public void deleteNasfund(Integer id) {
        nasfundRepository.deleteById(id);
    }
}
