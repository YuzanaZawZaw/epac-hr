package com.epac.hr.controller;

import com.epac.hr.entity.DailyAttendance;
import com.epac.hr.service.AttendanceService;
import com.epac.hr.service.EmployeeService;
import com.epac.hr.service.FortnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private FortnightService fortnightService;
    
    @GetMapping
    public String listAttendance(Model model) {
        model.addAttribute("attendances", attendanceService.getAllAttendance());
        return "attendance/list";
    }
    
    @GetMapping("/create")
    public String createAttendanceForm(Model model) {
        model.addAttribute("attendance", new DailyAttendance());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("fortnights", fortnightService.getAllFortnights());
        return "attendance/form";
    }
    
    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute DailyAttendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance";
    }
    
    @GetMapping("/{id}/edit")
    public String editAttendanceForm(@PathVariable Integer id, Model model) {
        Optional<DailyAttendance> attendance = attendanceService.getAttendanceById(id);
        if (attendance.isPresent()) {
            model.addAttribute("attendance", attendance.get());
            model.addAttribute("employees", employeeService.getAllEmployees());
            model.addAttribute("fortnights", fortnightService.getAllFortnights());
            return "attendance/form";
        }
        return "redirect:/attendance";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return "redirect:/attendance";
    }
}
