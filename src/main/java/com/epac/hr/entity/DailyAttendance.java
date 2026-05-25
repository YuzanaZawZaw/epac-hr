package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.math.BigDecimal;

@Entity
@Table(name = "daily_attendance", indexes = {
    @Index(name = "idx_employee_fortnight", columnList = "employee_id, fortnight_id"),
    @Index(name = "idx_attendance_date", columnList = "attendance_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAttendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Integer attendanceId;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "fortnight_id", nullable = false)
    private Fortnight fortnight;
    
    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;
    
    @Column(name = "day_name", length = 10)
    private String dayName;
    
    @Column(name = "morning_time_in")
    private LocalTime morningTimeIn;
    
    @Column(name = "morning_time_out")
    private LocalTime morningTimeOut;
    
    @Column(name = "afternoon_time_in")
    private LocalTime afternoonTimeIn;
    
    @Column(name = "afternoon_time_out")
    private LocalTime afternoonTimeOut;
    
    @Column(name = "working_hours", precision = 5, scale = 2)
    private BigDecimal workingHours;
    
    @Column(name = "overtime_hours", precision = 5, scale = 2)
    private BigDecimal overtimeHours;
    
    @Column(name = "remarks", length = 255)
    private String remarks;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
