package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll_details", indexes = {
    @Index(name = "idx_payroll", columnList = "payroll_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_detail_id")
    private Integer payrollDetailId;
    
    @ManyToOne
    @JoinColumn(name = "payroll_id", nullable = false)
    private PayrollFortnight payrollFortnight;
    
    @ManyToOne
    @JoinColumn(name = "component_id")
    private SalaryComponent salaryComponent;
    
    @Column(name = "component_name", length = 50)
    private String componentName;
    
    @Column(name = "component_type")
    @Enumerated(EnumType.STRING)
    private SalaryComponent.ComponentType componentType;
    
    @Column(name = "amount", precision = 12, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "description", length = 255)
    private String description;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
