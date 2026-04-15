package com.epw.tech_solution.service;

import java.util.List;

import com.epw.tech_solution.dto.CreateEmployeeRequest;
import com.epw.tech_solution.dto.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse create(CreateEmployeeRequest request);

    List<EmployeeResponse> list();
}