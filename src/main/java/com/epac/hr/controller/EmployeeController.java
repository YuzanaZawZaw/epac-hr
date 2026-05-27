package com.epac.hr.controller;

import com.epac.hr.entity.Employee;
import com.epac.hr.entity.Employee.EmployeeStatus;
import com.epac.hr.service.EmployeeService;
import com.epac.hr.service.PositionService;
import com.epac.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private PositionService positionService;
    
    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("totalEmployees", employeeService.getTotalEmployees());
        model.addAttribute("activeEmployees", employeeService.getActiveEmployeesCount());
        return "employee/list";
    }
    
    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("statuses", EmployeeStatus.values());
        return "employee/form";
    }
    
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }
    
    @GetMapping("/{id}/edit")
    public String editEmployeeForm(@PathVariable Integer id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("positions", positionService.getAllPositions());
            model.addAttribute("statuses", EmployeeStatus.values());
            return "employee/form";
        }
        return "redirect:/employees";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
    
    @GetMapping("/{id}")
    public String viewEmployee(@PathVariable Integer id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("positions", positionService.getAllPositions());
            return "employee/view";
        }
        return "redirect:/employees";
    }
    
    @GetMapping("/search")
    public String searchEmployee(@RequestParam String name, Model model) {
        model.addAttribute("employees", employeeService.searchEmployeeByName(name));
        return "employee/list";
    }
}
