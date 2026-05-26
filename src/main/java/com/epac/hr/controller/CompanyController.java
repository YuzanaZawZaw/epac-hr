package com.epac.hr.controller;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Company.CompanyStatus;
import com.epac.hr.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @GetMapping
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("totalCompanies", companyService.getTotalCompanies());
        model.addAttribute("activeCompanies", companyService.getActiveCompaniesCount());
        return "company/list";
    }
    
    @GetMapping("/create")
    public String createCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("statuses", CompanyStatus.values());
        return "company/form";
    }
    
    @PostMapping("/save")
    public String saveCompany(@ModelAttribute Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
    }
    
    @GetMapping("/{id}/edit")
    public String editCompanyForm(@PathVariable Integer id, Model model) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            model.addAttribute("company", company.get());
            model.addAttribute("statuses", CompanyStatus.values());
            return "company/form";
        }
        return "redirect:/companies";
    }
    
    @GetMapping("/{id}")
    public String viewCompany(@PathVariable Integer id, Model model) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            model.addAttribute("company", company.get());
            return "company/view";
        }
        return "redirect:/companies";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }
    
    @GetMapping("/search")
    public String searchCompany(@RequestParam String name, Model model) {
        Optional<Company> company = companyService.getCompanyByName(name);
        if (company.isPresent()) {
            model.addAttribute("companies", java.util.List.of(company.get()));
        } else {
            model.addAttribute("companies", java.util.List.of());
        }
        return "company/list";
    }
}
