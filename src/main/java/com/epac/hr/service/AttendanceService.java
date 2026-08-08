package com.epac.hr.service;

import com.epac.hr.entity.Attendance;
import com.epac.hr.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Attendance> saveAll(List<Attendance> rows) {
        return repository.saveAll(rows);
    }

    public List<Attendance> findByEmployeeAndPeriod(String employeeId, LocalDate from, LocalDate to) {
        return repository.findByEmployeeIdAndDateBetween(employeeId, from, to);
    }
}
