package com.epac.hr.repository;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Company.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByCompanyCode(String companyCode);
    Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findByCompanyRegistrationNumber(String registrationNumber);
    Optional<Company> findByEmail(String email);
    List<Company> findByCompanyStatus(CompanyStatus status);
    List<Company> findByCountry(String country);
    List<Company> findByCity(String city);
}
