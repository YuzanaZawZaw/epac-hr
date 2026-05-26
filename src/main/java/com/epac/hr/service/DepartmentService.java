package com.epac.hr.service;

import com.epac.hr.entity.Department;
import com.epac.hr.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public Department saveDepartment(Department department) {
        Objects.requireNonNull(department, "Department cannot be null");
        return departmentRepository.save(department);
    }
    
    public Optional<Department> getDepartmentById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return departmentRepository.findById(id);
    }
    
    public List<Department> getAllDepartments() {
        Objects.requireNonNull(departmentRepository, "departmentRepository must not be null");
        return departmentRepository.findAll();
    }
    
    public Optional<Department> getDepartmentByCode(String code) {
        Objects.requireNonNull(code, "code must not be null");
        return departmentRepository.findByDepartmentCode(code);
    }
    
    public Optional<Department> getDepartmentByName(String name) {
        Objects.requireNonNull(name, "name must not be null");
        return departmentRepository.findByDepartmentName(name);
    }
    
    public Department updateDepartment(Integer id, Department department) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(department, "department must not be null");
        Optional<Department> existing = departmentRepository.findById(id);
        if (existing.isPresent()) {
            Department dept = existing.get();
            dept.setDepartmentName(department.getDepartmentName());
            dept.setDepartmentCode(department.getDepartmentCode());
            return departmentRepository.save(dept);
        }
        return null;
    }
    
    public void deleteDepartment(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        departmentRepository.deleteById(id);
    }
}
