package com.epw.tech_solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epw.tech_solution.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}