package com.epac.hr.service;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Company.CompanyStatus;
import com.epac.hr.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;
    
    public Company saveCompany(Company company) {
        Objects.requireNonNull(company, "Company cannot be null");
        Objects.requireNonNull(company.getCompanyCode(), "Company code cannot be null");
        return companyRepository.save(company);
    }
    
    public Optional<Company> getCompanyById(Integer id) {
        Objects.requireNonNull(id, "Company ID cannot be null");
        return companyRepository.findById(id);
    }
    
    public List<Company> getAllCompanies() {
        Objects.requireNonNull(companyRepository, "Company repository cannot be null");
        return companyRepository.findAll();
    }
    
    public Optional<Company> getCompanyByCode(String code) {
        Objects.requireNonNull(code, "Company code cannot be null");
        return companyRepository.findByCompanyCode(code);
    }
    
    public Optional<Company> getCompanyByName(String name) {
        Objects.requireNonNull(name, "Company name cannot be null");
        return companyRepository.findByCompanyName(name);
    }
    
    public Optional<Company> getCompanyByRegistrationNumber(String registrationNumber) {
        Objects.requireNonNull(registrationNumber, "Company registration number cannot be null");
        return companyRepository.findByCompanyRegistrationNumber(registrationNumber);
    }
    
    public Optional<Company> getCompanyByEmail(String email) {
        Objects.requireNonNull(email, "Company email cannot be null");
        return companyRepository.findByEmail(email);
    }
    
    public List<Company> getCompaniesByStatus(CompanyStatus status) {
        Objects.requireNonNull(status, "Company status cannot be null");
        return companyRepository.findByCompanyStatus(status);
    }
    
    public List<Company> getCompaniesByCountry(String country) {
        Objects.requireNonNull(country, "Company country cannot be null");
        return companyRepository.findByCountry(country);
    }
    
    public List<Company> getCompaniesByCity(String city) {
        Objects.requireNonNull(city, "Company city cannot be null");
        return companyRepository.findByCity(city);
    }
    
    public Company updateCompany(Integer id, Company company) {
        Objects.requireNonNull(id, "Company ID cannot be null");
        Objects.requireNonNull(company, "Company cannot be null");
        Optional<Company> existing = companyRepository.findById(id);
        if (existing.isPresent()) {
            Company comp = existing.get();
            comp.setCompanyName(company.getCompanyName());
            comp.setCompanyCode(company.getCompanyCode());
            comp.setCompanyRegistrationNumber(company.getCompanyRegistrationNumber());
            comp.setCountry(company.getCountry());
            comp.setCity(company.getCity());
            comp.setProvince(company.getProvince());
            comp.setAddress(company.getAddress());
            comp.setPhoneNo(company.getPhoneNo());
            comp.setEmail(company.getEmail());
            comp.setWebsite(company.getWebsite());
            comp.setContactPerson(company.getContactPerson());
            comp.setContactPersonPhone(company.getContactPersonPhone());
            comp.setCompanyStatus(company.getCompanyStatus());
            comp.setRemarks(company.getRemarks());
            return companyRepository.save(comp);
        }
        return null;
    }
    
    public Company updateCompanyStatus(Integer id, CompanyStatus status) {
        Objects.requireNonNull(id, "Company ID cannot be null");
        Objects.requireNonNull(status, "Company status cannot be null");
        Objects.requireNonNull(companyRepository, "Company repository cannot be null");
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company comp = company.get();
            comp.setCompanyStatus(status);
            return companyRepository.save(comp);
        }
        return null;
    }
    
    public void deleteCompany(Integer id) {
        Objects.requireNonNull(id, "Company ID cannot be null");
        Objects.requireNonNull(companyRepository, "Company repository cannot be null");
        companyRepository.deleteById(id);
    }
    
    public long getTotalCompanies() {
        Objects.requireNonNull(companyRepository, "Company repository cannot be null");
        return companyRepository.count();
    }
    
    public long getActiveCompaniesCount() {
        Objects.requireNonNull(companyRepository, "Company repository cannot be null");
        return companyRepository.findByCompanyStatus(CompanyStatus.ACTIVE).size();
    }
}
