package com.epac.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;
    
    @Column(name = "company_name", nullable = false, unique = true, length = 100)
    private String companyName;
    
    @Column(name = "company_code", nullable = false, unique = true, length = 20)
    private String companyCode;
    
    @Column(name = "company_registration_number", length = 50)
    private String companyRegistrationNumber;
    
    @Column(name = "country", length = 50)
    private String country;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @Column(name = "province", length = 50)
    private String province;
    
    @Column(name = "address", length = 255)
    private String address;
    
    @Column(name = "phone_no", length = 20)
    private String phoneNo;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "website", length = 100)
    private String website;
    
    @Column(name = "contact_person", length = 100)
    private String contactPerson;
    
    @Column(name = "contact_person_phone", length = 20)
    private String contactPersonPhone;
    
    @Column(name = "company_status")
    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus = CompanyStatus.ACTIVE;
    
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
    
    public enum CompanyStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}
