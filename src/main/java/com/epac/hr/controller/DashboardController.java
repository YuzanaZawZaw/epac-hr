package com.epac.hr.controller;

import com.epac.hr.service.EmployeeService;
import com.epac.hr.service.PayrollService;
import com.epac.hr.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DashboardController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private PayrollService payrollService;
    
    @Autowired
    private LeaveService leaveService;
    
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalEmployees", employeeService.getTotalEmployees());
        model.addAttribute("activeEmployees", employeeService.getActiveEmployeesCount());
        model.addAttribute("totalPayrolls", payrollService.getAllPayrolls().size());
        model.addAttribute("pendingLeaves", leaveService.getLeaveRequestsByStatus(com.epac.hr.entity.LeaveRequest.ApprovalStatus.PENDING).size());
        return "dashboard";
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        return dashboard(model);
    }
}
