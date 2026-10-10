package com.faculty.service;

import com.faculty.dto.DepartmentRequest;
import com.faculty.exception.DuplicateResourceException;
import com.faculty.exception.ResourceNotFoundException;
import com.faculty.model.Department;
import com.faculty.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Transactional
    public Department createDepartment(DepartmentRequest request) {
        String name = request.getName().trim();
        String code = request.getCode().trim().toUpperCase();

        if (departmentRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Department with name '" + name + "' already exists");
        }
        if (departmentRepository.existsByCodeIgnoreCase(code)) {
            throw new DuplicateResourceException("Department with code '" + code + "' already exists");
        }

        Department department = new Department(name, code, request.getDescription() != null ? request.getDescription().trim() : "");
        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
