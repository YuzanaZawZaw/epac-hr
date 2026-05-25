package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "cash_advances", indexes = {
    @Index(name = "idx_employee_fortnight", columnList = "employee_id, fortnight_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashAdvance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advance_id")
    private Integer advanceId;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "fortnight_id", nullable = false)
    private Fortnight fortnight;
    
    @Column(name = "advance_date", nullable = false)
    private LocalDate advanceDate;
    
    @Column(name = "advance_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal advanceAmount;
    
    @Column(name = "reason", length = 255)
    private String reason;
    
    @Column(name = "approved_by", length = 100)
    private String approvedBy;
    
    @Column(name = "approval_date")
    private LocalDate approvalDate;
    
    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
    
    @ManyToOne
    @JoinColumn(name = "deducted_from_payroll_id")
    private PayrollFortnight deductedFromPayroll;
    
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
    
    public enum ApprovalStatus {
        PENDING, APPROVED, REJECTED
    }
}
