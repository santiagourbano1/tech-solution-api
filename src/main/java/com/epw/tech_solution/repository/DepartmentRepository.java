package com.epw.tech_solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epw.tech_solution.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}