package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll_fortnights", indexes = {
    @Index(name = "idx_employee_fortnight", columnList = "employee_id, fortnight_id"),
    @Index(name = "idx_payroll_status", columnList = "payroll_status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollFortnight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Integer payrollId;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "fortnight_id", nullable = false)
    private Fortnight fortnight;
    
    @Column(name = "period_start_date", nullable = false)
    private LocalDate periodStartDate;
    
    @Column(name = "period_end_date", nullable = false)
    private LocalDate periodEndDate;
    
    @Column(name = "total_working_hours", precision = 8, scale = 2)
    private BigDecimal totalWorkingHours = BigDecimal.ZERO;
    
    @Column(name = "total_overtime_hours", precision = 8, scale = 2)
    private BigDecimal totalOvertimeHours = BigDecimal.ZERO;
    
    @Column(name = "standard_hours", precision = 8, scale = 2)
    private BigDecimal standardHours = BigDecimal.ZERO;
    
    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;
    
    @Column(name = "standard_salary", precision = 12, scale = 2)
    private BigDecimal standardSalary = BigDecimal.ZERO;
    
    @Column(name = "overtime_salary", precision = 12, scale = 2)
    private BigDecimal overtimeSalary = BigDecimal.ZERO;
    
    @Column(name = "total_gross_wages", precision = 12, scale = 2)
    private BigDecimal totalGrossWages = BigDecimal.ZERO;
    
    @Column(name = "nasfund_deduction", precision = 12, scale = 2)
    private BigDecimal nasfundDeduction = BigDecimal.ZERO;
    
    @Column(name = "cash_advance_deduction", precision = 12, scale = 2)
    private BigDecimal cashAdvanceDeduction = BigDecimal.ZERO;
    
    @Column(name = "net_wages_to_be_paid", precision = 12, scale = 2)
    private BigDecimal netWagesToBePaid = BigDecimal.ZERO;
    
    @Column(name = "payroll_status")
    @Enumerated(EnumType.STRING)
    private PayrollStatus payrollStatus = PayrollStatus.DRAFT;
    
    @Column(name = "prepared_by", length = 100)
    private String preparedBy;
    
    @Column(name = "prepared_date")
    private LocalDateTime preparedDate;
    
    @Column(name = "checked_by", length = 100)
    private String checkedBy;
    
    @Column(name = "checked_date")
    private LocalDateTime checkedDate;
    
    @Column(name = "approved_by", length = 100)
    private String approvedBy;
    
    @Column(name = "approved_date")
    private LocalDateTime approvedDate;
    
    @Column(name = "remarks", columnDefinition = "TEXT")
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
    
    public enum PayrollStatus {
        DRAFT, SUBMITTED, APPROVED, PAID, REJECTED
    }
}
