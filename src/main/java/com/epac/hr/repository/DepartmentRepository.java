package com.epac.hr.repository;

import com.epac.hr.entity.Department;
import com.epac.hr.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByDepartmentCode(String departmentCode);
    Optional<Department> findByDepartmentName(String departmentName);

    // Find departments by Company entity
    List<Department> findByCompanyId(Company company);

    // Find departments by company id value
    List<Department> findByCompanyIdCompanyId(Integer companyId);
}
