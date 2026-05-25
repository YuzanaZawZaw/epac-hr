package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Integer leaveTypeId;
    
    @Column(name = "leave_type_name", nullable = false, unique = true, length = 50)
    private String leaveTypeName;
    
    @Column(name = "leave_code", nullable = false, unique = true, length = 20)
    private String leaveCode;
    
    @Column(name = "max_days_per_year")
    private Integer maxDaysPerYear;
    
    @Column(name = "is_paid")
    private Boolean isPaid = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
