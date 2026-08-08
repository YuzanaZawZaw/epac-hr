package com.epac.hr.controller;

import com.epac.hr.dto.AttendanceDto;
import com.epac.hr.entity.Attendance;
import com.epac.hr.service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendance")
    public String attendancePage(Model model,
                                 @RequestParam(required = false) String employeeId,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        // sample defaults for UI demonstration
        if (employeeId == null) employeeId = "A-002";
        if (from == null) from = LocalDate.parse("2026-07-25");
        if (to == null) to = LocalDate.parse("2026-08-07");

        model.addAttribute("employee", Map.of("id", employeeId, "name", "OLIVER ROBERT", "position","General Labour"));
        model.addAttribute("period", Map.of("from", from.toString(), "to", to.toString()));

        List<Attendance> rows = attendanceService.findByEmployeeAndPeriod(employeeId, from, to);
        if (rows == null || rows.isEmpty()) {
            // generate empty rows for the period
            List<Map<String, Object>> emptyRows = new ArrayList<>();
            LocalDate d = from;
            while (!d.isAfter(to)) {
                emptyRows.add(Map.of("date", d.toString(), "morningIn", "", "morningOut","", "afternoonIn","","afternoonOut","","remarks",""));
                d = d.plusDays(1);
            }
            model.addAttribute("rows", emptyRows);
        } else {
            // map entity to simple map for the template
            List<Map<String,Object>> mapped = rows.stream().map(a -> Map.of(
                    "date", a.getDate().toString(),
                    "morningIn", a.getMorningIn()==null?"":a.getMorningIn().toString(),
                    "morningOut", a.getMorningOut()==null?"":a.getMorningOut().toString(),
                    "afternoonIn", a.getAfternoonIn()==null?"":a.getAfternoonIn().toString(),
                    "afternoonOut", a.getAfternoonOut()==null?"":a.getAfternoonOut().toString(),
                    "remarks", a.getRemarks()==null?"":a.getRemarks(),
                    "id", a.getId()
            )).collect(Collectors.toList());
            model.addAttribute("rows", mapped);
        }
        return "attendance";
    }

    @PostMapping(path = "/api/attendance", consumes = "application/json")
    @ResponseBody
    public List<Attendance> saveAttendance(@RequestBody List<AttendanceDto> rows) {
        List<Attendance> entities = new ArrayList<>();
        for (AttendanceDto d : rows) {
            Attendance a = new Attendance();
            if (d.id != null) a.setId(d.id);
            a.setEmployeeId(d.employeeId != null ? d.employeeId : "A-002");
            try { a.setDate(LocalDate.parse(d.date)); } catch (Exception ex) { a.setDate(LocalDate.now()); }
            try { a.setMorningIn(d.morningIn!=null && !d.morningIn.isEmpty() ? LocalTime.parse(d.morningIn) : null); } catch (Exception ex) { a.setMorningIn(null); }
            try { a.setMorningOut(d.morningOut!=null && !d.morningOut.isEmpty() ? LocalTime.parse(d.morningOut) : null); } catch (Exception ex) { a.setMorningOut(null); }
            try { a.setAfternoonIn(d.afternoonIn!=null && !d.afternoonIn.isEmpty() ? LocalTime.parse(d.afternoonIn) : null); } catch (Exception ex) { a.setAfternoonIn(null); }
            try { a.setAfternoonOut(d.afternoonOut!=null && !d.afternoonOut.isEmpty() ? LocalTime.parse(d.afternoonOut) : null); } catch (Exception ex) { a.setAfternoonOut(null); }
            try { a.setWorkingHours(d.workingHours!=null && !d.workingHours.isEmpty() ? new BigDecimal(d.workingHours) : BigDecimal.ZERO); } catch (Exception ex) { a.setWorkingHours(BigDecimal.ZERO); }
            try { a.setOvertimeHours(d.overtimeHours!=null && !d.overtimeHours.isEmpty() ? new BigDecimal(d.overtimeHours) : BigDecimal.ZERO); } catch (Exception ex) { a.setOvertimeHours(BigDecimal.ZERO); }
            a.setRemarks(d.remarks);
            entities.add(a);
        }
        return attendanceService.saveAll(entities);
    }

    @GetMapping(path = "/api/attendance", produces = "application/json")
    @ResponseBody
    public List<Attendance> listAttendance(@RequestParam String employeeId,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return attendanceService.findByEmployeeAndPeriod(employeeId, from, to);
    }
}
