package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "absences", indexes = {
    @Index(name = "idx_employee_year", columnList = "employee_id, year")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absence_id")
    private Integer absenceId;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "year", nullable = false)
    private Integer year;
    
    @Column(name = "absence_count")
    private Integer absenceCount = 0;
    
    @Column(name = "sick_days")
    private Integer sickDays = 0;
    
    @Column(name = "special_days")
    private Integer specialDays = 0;
    
    @Column(name = "sum_of_days_for_absent")
    private Integer sumOfDaysForAbsent = 0;
    
    @Column(name = "remaining_days")
    private Integer remainingDays = 0;
    
    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
    
    @Column(name = "recorded_date")
    private LocalDate recordedDate;
    
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
