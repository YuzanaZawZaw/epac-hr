package com.epac.hr.service;

import com.epac.hr.entity.Absence;
import com.epac.hr.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {
    
    @Autowired
    private AbsenceRepository absenceRepository;
    
    public Absence saveAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }
    
    public Optional<Absence> getAbsenceById(Integer id) {
        return absenceRepository.findById(id);
    }
    
    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }
    
    public Optional<Absence> getAbsenceByEmployeeAndYear(Integer employeeId, Integer year) {
        return absenceRepository.findByEmployeeEmployeeIdAndYear(employeeId, year);
    }
    
    public List<Absence> getAbsencesByEmployee(Integer employeeId) {
        return absenceRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<Absence> getAbsencesByYear(Integer year) {
        return absenceRepository.findByYear(year);
    }
    
    public void deleteAbsence(Integer id) {
        absenceRepository.deleteById(id);
    }
}
