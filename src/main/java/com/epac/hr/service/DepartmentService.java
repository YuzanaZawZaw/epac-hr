package com.epac.hr.service;

import com.epac.hr.entity.Department;
import com.epac.hr.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }
    
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    public Optional<Department> getDepartmentByCode(String code) {
        return departmentRepository.findByDepartmentCode(code);
    }
    
    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }
    
    public Department updateDepartment(Integer id, Department department) {
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
        departmentRepository.deleteById(id);
    }
}
