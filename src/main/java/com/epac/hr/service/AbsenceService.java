package com.epac.hr.service;

import com.epac.hr.entity.Absence;
import com.epac.hr.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class AbsenceService {
    
    @Autowired
    private AbsenceRepository absenceRepository;
    
    public Absence saveAbsence(Absence absence) {
        Objects.requireNonNull(absence, "Absence cannot be null");
        return absenceRepository.save(absence);
    }
    
    public Optional<Absence> getAbsenceById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return absenceRepository.findById(id);
    }
    
    public List<Absence> getAllAbsences() {
        Objects.requireNonNull(absenceRepository, "absenceRepository must not be null");
        return absenceRepository.findAll();
    }
    
    public Optional<Absence> getAbsenceByEmployeeAndYear(Integer employeeId, Integer year) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        Objects.requireNonNull(year, "year must not be null");
        return absenceRepository.findByEmployeeEmployeeIdAndYear(employeeId, year);
    }
    
    public List<Absence> getAbsencesByEmployee(Integer employeeId) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        return absenceRepository.findByEmployeeEmployeeId(employeeId);
    }
    
    public List<Absence> getAbsencesByYear(Integer year) {
        Objects.requireNonNull(year, "year must not be null");
        return absenceRepository.findByYear(year);
    }
    
    public void deleteAbsence(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        absenceRepository.deleteById(id);
    }
}
