package com.epac.hr.controller;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Department;
import com.epac.hr.repository.CompanyRepository;
import com.epac.hr.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final DepartmentService departmentService;

    public CompanyController(CompanyRepository companyRepository, DepartmentService departmentService) {
        this.companyRepository = companyRepository;
        this.departmentService = departmentService;
    }

    // Show company details and departments
    @GetMapping("/{companyId}")
    public String details(@PathVariable Integer companyId, Model model) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found: " + companyId));
        List<Department> departments = departmentService.findByCompany(companyId);
        model.addAttribute("company", company);
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department());
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
        return "company-details"; // reuse the same page which will show edit form if editDepartment present
    }

    @PostMapping("/{companyId}/departments/{departmentId}/update")
    public String updateDepartment(@PathVariable Integer companyId, @PathVariable Integer departmentId, @ModelAttribute("editDepartment") Department department) {
        departmentService.update(departmentId, department);
        return "redirect:/companies/" + companyId;
    }
}
