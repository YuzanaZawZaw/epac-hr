package com.epac.hr.entity;

import jakarta.persistence.*;
// Removed Lombok dependency. Using explicit constructors and accessor methods.
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "absences", indexes = {
    @Index(name = "idx_employee_year", columnList = "employee_id, year")
})
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
    
    public Absence() {
    }

    public Absence(Integer absenceId, Employee employee, Integer year, Integer absenceCount, Integer sickDays, Integer specialDays, Integer sumOfDaysForAbsent, Integer remainingDays, String remarks, LocalDate recordedDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.absenceId = absenceId;
        this.employee = employee;
        this.year = year;
        this.absenceCount = absenceCount;
        this.sickDays = sickDays;
        this.specialDays = specialDays;
        this.sumOfDaysForAbsent = sumOfDaysForAbsent;
        this.remainingDays = remainingDays;
        this.remarks = remarks;
        this.recordedDate = recordedDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(Integer absenceId) {
        this.absenceId = absenceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAbsenceCount() {
        return absenceCount;
    }

    public void setAbsenceCount(Integer absenceCount) {
        this.absenceCount = absenceCount;
    }

    public Integer getSickDays() {
        return sickDays;
    }

    public void setSickDays(Integer sickDays) {
        this.sickDays = sickDays;
    }

    public Integer getSpecialDays() {
        return specialDays;
    }

    public void setSpecialDays(Integer specialDays) {
        this.specialDays = specialDays;
    }

    public Integer getSumOfDaysForAbsent() {
        return sumOfDaysForAbsent;
    }

    public void setSumOfDaysForAbsent(Integer sumOfDaysForAbsent) {
        this.sumOfDaysForAbsent = sumOfDaysForAbsent;
    }

    public Integer getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(Integer remainingDays) {
        this.remainingDays = remainingDays;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
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
