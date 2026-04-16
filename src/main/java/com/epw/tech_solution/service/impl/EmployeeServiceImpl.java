package com.epw.tech_solution.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epw.tech_solution.dto.CreateEmployeeRequest;
import com.epw.tech_solution.dto.EmployeeResponse;
import com.epw.tech_solution.entity.Department;
import com.epw.tech_solution.entity.Employee;
import com.epw.tech_solution.exception.ResourceNotFoundException;
import com.epw.tech_solution.repository.DepartmentRepository;
import com.epw.tech_solution.repository.EmployeeRepository;
import com.epw.tech_solution.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository repository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + request.getDepartmentId()));

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setPosition(request.getPosition());
        employee.setDepartment(department);

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
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return toResponse(employee);
    }

    @Override
    public EmployeeResponse update(Long id, CreateEmployeeRequest request) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + request.getDepartmentId()));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setPosition(request.getPosition());
        employee.setDepartment(department);

        Employee updated = repository.save(employee);

        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

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

        // Enviar información del departamento
        response.setDepartmentId(employee.getDepartment().getId());
        response.setDepartmentName(employee.getDepartment().getName()); // ✅ NUEVO

        return response;
    }
}