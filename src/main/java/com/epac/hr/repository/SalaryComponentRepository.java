package com.epac.hr.repository;

import com.epac.hr.entity.SalaryComponent;
import com.epac.hr.entity.SalaryComponent.ComponentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryComponentRepository extends JpaRepository<SalaryComponent, Integer> {
    Optional<SalaryComponent> findByComponentCode(String componentCode);
    Optional<SalaryComponent> findByComponentName(String componentName);
    List<SalaryComponent> findByComponentType(ComponentType componentType);
    List<SalaryComponent> findByIsActive(Boolean isActive);
}
