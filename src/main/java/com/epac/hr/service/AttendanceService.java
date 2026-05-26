package com.epac.hr.service;

import com.epac.hr.entity.DailyAttendance;
import com.epac.hr.repository.DailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class AttendanceService {
    
    @Autowired
    private DailyAttendanceRepository attendanceRepository;
    
    public DailyAttendance saveAttendance(DailyAttendance attendance) {
        Objects.requireNonNull(attendance, "Attendance cannot be null");
        return attendanceRepository.save(attendance);
    }
    
    public Optional<DailyAttendance> getAttendanceById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return attendanceRepository.findById(id);
    }
    
    public List<DailyAttendance> getAllAttendance() {
        Objects.requireNonNull(attendanceRepository, "attendanceRepository must not be null");
        return attendanceRepository.findAll();
    }
    
    public Optional<DailyAttendance> getAttendanceByEmployeeAndDate(Integer employeeId, LocalDate date) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        Objects.requireNonNull(date, "date must not be null");
        return attendanceRepository.findByEmployeeEmployeeIdAndAttendanceDate(employeeId, date);
    }
    
    public List<DailyAttendance> getAttendanceByEmployeeAndFortnight(Integer employeeId, Integer fortnightId) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        Objects.requireNonNull(fortnightId, "fortnightId must not be null");
        return attendanceRepository.findByEmployeeEmployeeIdAndFortnightFortnightId(employeeId, fortnightId);
    }
    
    public List<DailyAttendance> getAttendanceByDateRange(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(employeeId, "employeeId must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        return attendanceRepository.findByEmployeeAndDateRange(employeeId, startDate, endDate);
    }
    
    public DailyAttendance updateAttendance(Integer id, DailyAttendance attendance) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(attendance, "attendance must not be null");
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
        Objects.requireNonNull(id, "id must not be null");
        attendanceRepository.deleteById(id);
    }
}
