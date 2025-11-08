package com.example.Student.Management.dto;

import jakarta.validation.constraints.*;

public class StudentRequest {

    @NotBlank
    private String rollNumber;

    @NotBlank
    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String department;

    @Min(1) @Max(4)
    private Integer year;

    @DecimalMin("0.0") @DecimalMax("10.0")
    private Double gpa;

    // getters + setters
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }
}

