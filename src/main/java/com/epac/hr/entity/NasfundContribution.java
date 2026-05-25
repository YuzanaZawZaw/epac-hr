package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "nasfund_contributions", indexes = {
    @Index(name = "idx_employee", columnList = "employee_id"),
    @Index(name = "idx_contribution_date", columnList = "contribution_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NasfundContribution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nasfund_id")
    private Integer nasfundId;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "payroll_id")
    private PayrollFortnight payrollFortnight;
    
    @Column(name = "contribution_date")
    private LocalDate contributionDate;
    
    @Column(name = "contribution_month", length = 50)
    private String contributionMonth;
    
    @Column(name = "contribution_amount", precision = 12, scale = 2)
    private BigDecimal contributionAmount;
    
    @Column(name = "contribution_percentage", precision = 5, scale = 2)
    private BigDecimal contributionPercentage = new BigDecimal("6.00");
    
    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
