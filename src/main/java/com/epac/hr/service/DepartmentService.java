package com.epac.hr.service;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Department;
import com.epac.hr.repository.CompanyRepository;
import com.epac.hr.repository.DepartmentRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    public List<Department> findByCompany(Integer companyId) {
        // prefer repository method by id
        return departmentRepository.findByCompanyIdCompanyId(companyId);
    }

    @Transactional
    public Department createForCompany(@NonNull Integer companyId, Department department) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found: " + companyId));
        department.setCompanyId(company);
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(@NonNull Integer id, Department dto) {
        Department existing = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found: " + id));
        existing.setDepartmentName(dto.getDepartmentName());
        existing.setDepartmentCode(dto.getDepartmentCode());
        // company relationship left unchanged unless provided
        if (dto.getCompanyId() != null) existing.setCompanyId(dto.getCompanyId());
        return departmentRepository.save(existing);
    }

    @Transactional
    public void delete(@NonNull Integer id) {
        departmentRepository.deleteById(id);
    }
}
