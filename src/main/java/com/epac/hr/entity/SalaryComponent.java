package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "salary_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Integer componentId;
    
    @Column(name = "component_name", nullable = false, unique = true, length = 50)
    private String componentName;
    
    @Column(name = "component_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ComponentType componentType;
    
    @Column(name = "component_code", nullable = false, unique = true, length = 20)
    private String componentCode;
    
    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;
    
    @Column(name = "fixed_amount", precision = 12, scale = 2)
    private BigDecimal fixedAmount;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public enum ComponentType {
        ADDITION, DEDUCTION
    }
}
