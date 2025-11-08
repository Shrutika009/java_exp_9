package com.example.Student.Management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students", uniqueConstraints = {
        @UniqueConstraint(columnNames = "roll_number"),
        @UniqueConstraint(columnNames = "email")
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "roll_number", nullable = false, unique = true)
    private String rollNumber;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "department")
    private String department;

    @Min(1) @Max(4)
    @Column(name = "academic_year")
    private Integer year;


    // NEW: GPA field (Double)
    @Column(name = "gpa")
    private Double gpa;

    public Student() {}

    // Existing convenience constructor (without gpa)
    public Student(String rollNumber, String firstName, String lastName, String email, String department, Integer year) {
        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.year = year;
    }

    // NEW: Full constructor including gpa
    public Student(String rollNumber, String firstName, String lastName, String email, String department, Integer year, Double gpa) {
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
