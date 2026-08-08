package com.epac.hr.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "morning_in")
    private LocalTime morningIn;

    @Column(name = "morning_out")
    private LocalTime morningOut;

    @Column(name = "afternoon_in")
    private LocalTime afternoonIn;

    @Column(name = "afternoon_out")
    private LocalTime afternoonOut;

    @Column(name = "working_hours", precision = 10, scale = 2)
    private BigDecimal workingHours;

    @Column(name = "overtime_hours", precision = 10, scale = 2)
    private BigDecimal overtimeHours;

    @Column(name = "remarks")
    private String remarks;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getMorningIn() { return morningIn; }
    public void setMorningIn(LocalTime morningIn) { this.morningIn = morningIn; }

    public LocalTime getMorningOut() { return morningOut; }
    public void setMorningOut(LocalTime morningOut) { this.morningOut = morningOut; }

    public LocalTime getAfternoonIn() { return afternoonIn; }
    public void setAfternoonIn(LocalTime afternoonIn) { this.afternoonIn = afternoonIn; }

    public LocalTime getAfternoonOut() { return afternoonOut; }
    public void setAfternoonOut(LocalTime afternoonOut) { this.afternoonOut = afternoonOut; }

    public BigDecimal getWorkingHours() { return workingHours; }
    public void setWorkingHours(BigDecimal workingHours) { this.workingHours = workingHours; }

    public BigDecimal getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(BigDecimal overtimeHours) { this.overtimeHours = overtimeHours; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
