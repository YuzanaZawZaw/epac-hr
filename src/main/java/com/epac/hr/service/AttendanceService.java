package com.epac.hr.service;

import com.epac.hr.entity.DailyAttendance;
import com.epac.hr.repository.DailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    
    @Autowired
    private DailyAttendanceRepository attendanceRepository;
    
    public DailyAttendance saveAttendance(DailyAttendance attendance) {
        return attendanceRepository.save(attendance);
    }
    
    public Optional<DailyAttendance> getAttendanceById(Integer id) {
        return attendanceRepository.findById(id);
    }
    
    public List<DailyAttendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
    
    public Optional<DailyAttendance> getAttendanceByEmployeeAndDate(Integer employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeEmployeeIdAndAttendanceDate(employeeId, date);
    }
    
    public List<DailyAttendance> getAttendanceByEmployeeAndFortnight(Integer employeeId, Integer fortnightId) {
        return attendanceRepository.findByEmployeeEmployeeIdAndFortnightFortnightId(employeeId, fortnightId);
    }
    
    public List<DailyAttendance> getAttendanceByDateRange(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeAndDateRange(employeeId, startDate, endDate);
    }
    
    public DailyAttendance updateAttendance(Integer id, DailyAttendance attendance) {
        Optional<DailyAttendance> existing = attendanceRepository.findById(id);
        if (existing.isPresent()) {
            DailyAttendance att = existing.get();
            att.setMorningTimeIn(attendance.getMorningTimeIn());
            att.setMorningTimeOut(attendance.getMorningTimeOut());
            att.setAfternoonTimeIn(attendance.getAfternoonTimeIn());
            att.setAfternoonTimeOut(attendance.getAfternoonTimeOut());
            att.setWorkingHours(attendance.getWorkingHours());
            att.setOvertimeHours(attendance.getOvertimeHours());
            att.setRemarks(attendance.getRemarks());
            return attendanceRepository.save(att);
        }
        return null;
    }
    
    public void deleteAttendance(Integer id) {
        attendanceRepository.deleteById(id);
    }
}
