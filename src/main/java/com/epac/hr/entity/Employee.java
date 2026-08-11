package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "employees", indexes = {
    @Index(name = "idx_employee_code", columnList = "employee_code"),
    // @Index(name = "idx_employee_status", columnList = "employee_status")
    // @Index(name = "idx_department", columnList = "department_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;
    
    @Column(name = "employee_code", nullable = false, unique = true, length = 20)
    private String employeeCode;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "age")
    private Integer age;

    @Column(name = "home_address", length = 255)
    private String homeAddress;
    
    @Column(name = "province_address", length = 100)
    private String provinceAddress;
    
    @Column(name = "phone_no", length = 20)
    private String phoneNo;
    
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "payroll_no", length = 20)
    private String payrollNo;

      @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate = new BigDecimal("5.00");
    
    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
    
    @Column(name = "date_of_birth_recorded")
    private LocalDate dateOfBirthRecorded;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // @Column(name = "nasfund_number", length = 20)
    // private String nasfundNumber;
    
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "position_id")
    // private Position position;
    
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "department_id")
    // private Department department;
    
    // @Column(name = "company", length = 100)
    // private String company;
    
    // @Column(name = "company_id", length = 20)
    // private String companyId;
    
    // @Column(name = "started_joined_date")
    // private LocalDate startedJoinedDate;
    
    // @Column(name = "length_of_service", length = 50)
    // private String lengthOfService;
    

    // @Column(name = "employee_status")
    // @Enumerated(EnumType.STRING)
    // private EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
    

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum Gender {
        Male, Female, Other
    }
    
    // public enum EmployeeStatus {
    //     ACTIVE, SUSPENDED, TERMINATED, ON_LEAVE
    // }
}
