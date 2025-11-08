package com.example.Student.Management.dto;

public class StudentResponse {
    private Long id;
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Integer year;
    private Double gpa;

    public StudentResponse() {}

    public StudentResponse(Long id, String rollNumber, String firstName, String lastName,
                           String email, String department, Integer year, Double gpa) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.year = year;
        this.gpa = gpa;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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

