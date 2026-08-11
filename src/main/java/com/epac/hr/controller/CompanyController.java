package com.epac.hr.controller;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Company.CompanyStatus;
import com.epac.hr.entity.Department;
import com.epac.hr.repository.CompanyRepository;
import com.epac.hr.service.CompanyService;
import com.epac.hr.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyRepository companyRepository, DepartmentService departmentService) {
        this.companyRepository = companyRepository;
        this.departmentService = departmentService;
    }

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
    public String createCompany(@ModelAttribute Company company) {
        if(company.getCompanyId() != null) {
            companyService.updateCompany(company.getCompanyId(), company);
        } else {
            companyService.saveCompany(company);
        }           
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
    
    // @GetMapping("/{id}")
    // public String viewCompany(@PathVariable Integer id, Model model) {
    //     Optional<Company> company = companyService.getCompanyById(id);
    //     if (company.isPresent()) {
    //         model.addAttribute("company", company.get());
    //         return "company/view";
    //     }
    //     return "redirect:/companies";
    // }
    
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

    // Show company details and departments
    @GetMapping("/{companyId}")
    public String details(@PathVariable Integer companyId, Model model) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found: " + companyId));
        List<Department> departments = departmentService.findByCompany(companyId);
        model.addAttribute("company", company);
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department());
        model.addAttribute("showCompanyDetails", true);
        model.addAttribute("activePage", "companies-details");
        return "company-details";
    }

    // Handle creating a department for this company
    @PostMapping("/{companyId}/departments")
    public String createDepartment(@PathVariable Integer companyId, @ModelAttribute("newDepartment") Department department) {
        departmentService.createForCompany(companyId, department);
        return "redirect:/companies/" + companyId;
    }

    // Delete department
    @PostMapping("/{companyId}/departments/{departmentId}/delete")
    public String deleteDepartment(@PathVariable Integer companyId, @PathVariable Integer departmentId) {
        departmentService.delete(departmentId);
        return "redirect:/companies/" + companyId;
    }

    // Show edit form
    @GetMapping("/{companyId}/departments/{departmentId}/edit")
    public String editDepartmentForm(@PathVariable Integer companyId, @PathVariable Integer departmentId, Model model) {
        Department dept = departmentService.findByCompany(companyId).stream().filter(d -> d.getDepartmentId().equals(departmentId)).findFirst().orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found" + companyId));
        model.addAttribute("company", company);
        model.addAttribute("departments", departmentService.findByCompany(companyId));
        model.addAttribute("editDepartment", dept);
        // For the Add Department form
        model.addAttribute("newDepartment", new Department());
        return "company-details"; // reuse the same page which will show edit form if editDepartment present
    }

    @PostMapping("/{companyId}/departments/{departmentId}/update")
    public String updateDepartment(@PathVariable Integer companyId, @PathVariable Integer departmentId, @ModelAttribute("editDepartment") Department department) {
        departmentService.update(departmentId, department);
        return "redirect:/companies/" + companyId;
    }
}
