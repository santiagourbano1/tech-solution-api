package com.epw.tech_solution.dto;

import java.time.Instant;

public class EmployeeResponse {

    private Long id;
    private String name;
    private String position;
    private Double salary;
    private Instant createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Double getSalary() {
        return salary;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}