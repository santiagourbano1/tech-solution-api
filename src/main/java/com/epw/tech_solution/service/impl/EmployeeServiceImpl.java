package com.epw.tech_solution.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epw.tech_solution.dto.CreateEmployeeRequest;
import com.epw.tech_solution.dto.EmployeeResponse;
import com.epw.tech_solution.entity.Employee;
import com.epw.tech_solution.repository.EmployeeRepository;
import com.epw.tech_solution.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setPosition(request.getPosition());

        Employee saved = repository.save(employee);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> list() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        return toResponse(employee);
    }

    @Override
    public EmployeeResponse update(Long id, CreateEmployeeRequest request) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setPosition(request.getPosition());

        Employee updated = repository.save(employee);

        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        repository.delete(employee);
    }

    private EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());
        response.setPosition(employee.getPosition());
        response.setCreatedAt(employee.getCreatedAt());

        return response;
    }
}