package com.epac.hr.controller;

import com.epac.hr.entity.LeaveRequest;
import com.epac.hr.entity.LeaveRequest.ApprovalStatus;
import com.epac.hr.service.LeaveService;
import com.epac.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/leaves")
public class LeaveController {
    
    @Autowired
    private LeaveService leaveService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping
    public String listLeaves(Model model) {
        model.addAttribute("leaves", leaveService.getAllLeaveRequests());
        return "leave/list";
    }
    
    @GetMapping("/create")
    public String createLeaveForm(Model model) {
        model.addAttribute("leave", new LeaveRequest());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("statuses", ApprovalStatus.values());
        return "leave/form";
    }
    
    @PostMapping("/save")
    public String saveLeave(@ModelAttribute LeaveRequest leaveRequest) {
        leaveService.saveLeaveRequest(leaveRequest);
        return "redirect:/leaves";
    }
    
    @GetMapping("/{id}")
    public String viewLeave(@PathVariable Integer id, Model model) {
        Optional<LeaveRequest> leave = leaveService.getLeaveRequestById(id);
        if (leave.isPresent()) {
            model.addAttribute("leave", leave.get());
            return "leave/view";
        }
        return "redirect:/leaves";
    }
    
    @GetMapping("/{id}/approve")
    public String approveLeave(@PathVariable Integer id, @RequestParam String approvedBy) {
        leaveService.approveLeaveRequest(id, approvedBy);
        return "redirect:/leaves";
    }
    
    @GetMapping("/{id}/reject")
    public String rejectLeave(@PathVariable Integer id, @RequestParam String approvedBy) {
        leaveService.rejectLeaveRequest(id, approvedBy);
        return "redirect:/leaves";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteLeave(@PathVariable Integer id) {
        leaveService.deleteLeaveRequest(id);
        return "redirect:/leaves";
    }
}
