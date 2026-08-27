package com.epac.hr.service;

import com.epac.hr.entity.EmployeeEmployment;
import com.epac.hr.repository.EmployeeEmploymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeEmploymentService {

    private final EmployeeEmploymentRepository repository;

    public EmployeeEmploymentService(EmployeeEmploymentRepository repository) {
        this.repository = repository;
    }

    public EmployeeEmployment save(EmployeeEmployment e) {
        return repository.save(e);
    }

    public List<EmployeeEmployment> findByEmployee(Integer employeeId) {
        return repository.findByEmployeeEmployeeId(employeeId);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
