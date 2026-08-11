package com.epac.hr.controller;

import com.epac.hr.entity.Department;
import com.epac.hr.entity.Position;
import com.epac.hr.repository.DepartmentRepository;
import com.epac.hr.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final PositionService positionService;

    public DepartmentController(DepartmentRepository departmentRepository, PositionService positionService) {
        this.departmentRepository = departmentRepository;
        this.positionService = positionService;
    }

    @GetMapping("/{departmentId}")
    public String details(@PathVariable Integer departmentId, Model model) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        List<Position> positions = positionService.findByDepartment(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("positions", positions);
        model.addAttribute("newPosition", new Position());
        return "department-details";
    }

    @PostMapping("/{departmentId}/positions")
    public String createPosition(@PathVariable Integer departmentId, @ModelAttribute("newPosition") Position position) {
        positionService.createForDepartment(departmentId, position);
        return "redirect:/departments/" + departmentId;
    }

    @PostMapping("/{departmentId}/positions/{positionId}/delete")
    public String deletePosition(@PathVariable Integer departmentId, @PathVariable Integer positionId) {
        positionService.delete(positionId);
        return "redirect:/departments/" + departmentId;
    }

    @GetMapping("/{departmentId}/positions/{positionId}/edit")
    public String editPositionForm(@PathVariable Integer departmentId, @PathVariable Integer positionId, Model model) {
        Position pos = positionService.findByDepartment(departmentId).stream()
                .filter(p -> p.getPositionId().equals(positionId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + positionId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        model.addAttribute("department", department);
        model.addAttribute("positions", positionService.findByDepartment(departmentId));
        model.addAttribute("editPosition", pos);
        model.addAttribute("newPosition", new Position());
        return "department-details";
    }

    @PostMapping("/{departmentId}/positions/{positionId}/update")
    public String updatePosition(@PathVariable Integer departmentId, @PathVariable Integer positionId, @ModelAttribute("editPosition") Position position) {
        positionService.update(positionId, position);
        return "redirect:/departments/" + departmentId;
    }
}
