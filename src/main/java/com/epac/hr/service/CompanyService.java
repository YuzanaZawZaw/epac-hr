package com.epac.hr.service;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Company.CompanyStatus;
import com.epac.hr.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;
    
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
    
    public Optional<Company> getCompanyById(Integer id) {
        return companyRepository.findById(id);
    }
    
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    
    public Optional<Company> getCompanyByCode(String code) {
        return companyRepository.findByCompanyCode(code);
    }
    
    public Optional<Company> getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name);
    }
    
    public Optional<Company> getCompanyByRegistrationNumber(String registrationNumber) {
        return companyRepository.findByCompanyRegistrationNumber(registrationNumber);
    }
    
    public Optional<Company> getCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }
    
    public List<Company> getCompaniesByStatus(CompanyStatus status) {
        return companyRepository.findByCompanyStatus(status);
    }
    
    public List<Company> getCompaniesByCountry(String country) {
        return companyRepository.findByCountry(country);
    }
    
    public List<Company> getCompaniesByCity(String city) {
        return companyRepository.findByCity(city);
    }
    
    public Company updateCompany(Integer id, Company company) {
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
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company comp = company.get();
            comp.setCompanyStatus(status);
            return companyRepository.save(comp);
        }
        return null;
    }
    
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
    
    public long getTotalCompanies() {
        return companyRepository.count();
    }
    
    public long getActiveCompaniesCount() {
        return companyRepository.findByCompanyStatus(CompanyStatus.ACTIVE).size();
    }
}
