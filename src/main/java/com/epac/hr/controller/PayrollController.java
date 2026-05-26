package com.epac.hr.controller;

import com.epac.hr.entity.PayrollFortnight;
import com.epac.hr.service.PayrollService;
import com.epac.hr.service.EmployeeService;
import com.epac.hr.service.FortnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
    
    @Autowired
    private PayrollService payrollService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private FortnightService fortnightService;
    
    @GetMapping
    public String listPayrolls(Model model) {
        model.addAttribute("payrolls", payrollService.getAllPayrolls());
        return "payroll/list";
    }
    
    @GetMapping("/create")
    public String createPayrollForm(Model model) {
        model.addAttribute("payroll", new PayrollFortnight());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("fortnights", fortnightService.getAllFortnights());
        return "payroll/form";
    }
    
    @PostMapping("/save")
    public String savePayroll(@ModelAttribute PayrollFortnight payroll) {
        payrollService.savePayroll(payroll);
        return "redirect:/payroll";
    }
    
    @GetMapping("/{id}")
    public String viewPayroll(@PathVariable Integer id, Model model) {
        Optional<PayrollFortnight> payroll = payrollService.getPayrollById(id);
        if (payroll.isPresent()) {
            model.addAttribute("payroll", payroll.get());
            return "payroll/view";
        }
        return "redirect:/payroll";
    }
    
    @GetMapping("/{id}/approve")
    public String approvePayroll(@PathVariable Integer id, @RequestParam String approvedBy) {
        payrollService.approvePayroll(id, approvedBy);
        return "redirect:/payroll";
    }
    
    @GetMapping("/{id}/delete")
    public String deletePayroll(@PathVariable Integer id) {
        payrollService.deletePayroll(id);
        return "redirect:/payroll";
    }
}
