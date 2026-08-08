package com.epac.hr.dto;

public class AttendanceDto {
    public Long id;
    public String employeeId;
    public String date; // ISO yyyy-MM-dd
    public String morningIn; // HH:mm
    public String morningOut;
    public String afternoonIn;
    public String afternoonOut;
    public String workingHours;
    public String overtimeHours;
    public String remarks;
}
