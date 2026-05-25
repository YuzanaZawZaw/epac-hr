package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fortnights", indexes = {
    @Index(name = "idx_date_range", columnList = "start_date, end_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fortnight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fortnight_id")
    private Integer fortnightId;
    
    @Column(name = "fortnight_number", nullable = false)
    private Integer fortnightNumber;
    
    @Column(name = "year", nullable = false)
    private Integer year;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "fortnight_name", length = 50)
    private String fortnightName;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FortnightStatus status = FortnightStatus.OPEN;
    
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
    
    public enum FortnightStatus {
        OPEN, CLOSED, LOCKED
    }
}
