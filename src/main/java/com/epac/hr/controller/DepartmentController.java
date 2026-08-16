package com.epac.hr.controller;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Department;
import com.epac.hr.entity.Position;
import com.epac.hr.repository.CompanyRepository;
import com.epac.hr.repository.DepartmentRepository;
import com.epac.hr.service.PositionService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final PositionService positionService;

    public DepartmentController(DepartmentRepository departmentRepository, CompanyRepository companyRepository, PositionService positionService) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
        this.positionService = positionService;
    }

    @GetMapping("/{companyId}/departments/{departmentId}/view")
    public String details(@PathVariable Integer companyId, @PathVariable Integer departmentId, Model model) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found: " + companyId));
       
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        List<Position> positions = positionService.findByDepartment(departmentId);
        model.addAttribute("company", company);
        model.addAttribute("companyId", company.getCompanyId());
        model.addAttribute("department", department);
        model.addAttribute("positions", positions);

        model.addAttribute("newPosition", new Position());
        model.addAttribute("showCompanyDetails", true);
        model.addAttribute("showDepartmentDetails", true);

        //model.addAttribute("companyActivePage", "companies-details");
        model.addAttribute("activePage", "departments-details");

        return "department-details";
    }

    @PostMapping("/{departmentId}/positions")
    public String createPosition(@PathVariable Integer departmentId, @ModelAttribute("newPosition") Position position) {
        Company company = departmentRepository.findById(departmentId)
                .map(Department::getCompanyId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        
        positionService.createForDepartment(departmentId, position);
        return "redirect:/departments/" + company.getCompanyId() + "/departments/" + departmentId + "/view";
    }

    @PostMapping("/{departmentId}/positions/{positionId}/delete")
    public String deletePosition(@PathVariable @NonNull Integer departmentId, @PathVariable @NonNull Integer positionId) {
        positionService.delete(positionId);
        Company company = departmentRepository.findById(departmentId)
                .map(Department::getCompanyId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        return "redirect:/departments/" + company.getCompanyId() + "/departments/" + departmentId + "/view";
    }

    @GetMapping("/{departmentId}/positions/{positionId}/edit")
    public String editPositionForm(@PathVariable @NonNull Integer departmentId, @PathVariable Integer positionId, Model model) {
        Position pos = positionService.findByDepartment(departmentId).stream()
                .filter(p -> p.getPositionId().equals(positionId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + positionId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        model.addAttribute("company", department.getCompanyId());
        model.addAttribute("department", department);
        model.addAttribute("positions", positionService.findByDepartment(departmentId));
        model.addAttribute("editPosition", pos);
        model.addAttribute("newPosition", new Position());
        return "department-details";
    }
    

    @PostMapping("/{departmentId}/positions/{positionId}/update")
    public String updatePosition(@PathVariable @NonNull Integer departmentId, @PathVariable Integer positionId, @ModelAttribute("editPosition") Position position) {
        Company company = departmentRepository.findById(departmentId)
                .map(Department::getCompanyId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        positionService.update(positionId, position);
        return "redirect:/departments/" + company.getCompanyId() + "/departments/" + departmentId + "/view";
    }
}
