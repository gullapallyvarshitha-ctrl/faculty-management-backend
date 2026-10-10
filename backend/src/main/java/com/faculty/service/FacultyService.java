package com.faculty.service;

import com.faculty.dto.FacultyRequest;
import com.faculty.exception.DuplicateResourceException;
import com.faculty.exception.ResourceNotFoundException;
import com.faculty.model.Faculty;
import com.faculty.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculty(String query, String department, String designation) {
        String cleanQuery = (query != null && !query.trim().isEmpty()) ? query.trim() : null;
        String cleanDept = (department != null && !department.trim().isEmpty()) ? department.trim() : null;
        String cleanDesig = (designation != null && !designation.trim().isEmpty()) ? designation.trim() : null;

        if (cleanQuery == null && cleanDept == null && cleanDesig == null) {
            return facultyRepository.findAll();
        }

        return facultyRepository.searchAndFilter(cleanQuery, cleanDept, cleanDesig);
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id: " + id));
    }

    @Transactional
    public Faculty createFaculty(FacultyRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        if (facultyRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("A faculty member with email '" + email + "' already exists");
        }

        Faculty faculty = new Faculty();
        mapRequestToEntity(request, faculty);
        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Long id, FacultyRequest request) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id: " + id));

        String email = request.getEmail().trim().toLowerCase();
        if (facultyRepository.existsByEmailAndIdNot(email, id)) {
            throw new DuplicateResourceException("A faculty member with email '" + email + "' already exists");
        }

        mapRequestToEntity(request, faculty);
        return facultyRepository.save(faculty);
    }

    @Transactional
    public void deleteFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Faculty not found with id: " + id);
        }
        facultyRepository.deleteById(id);
    }

    private void mapRequestToEntity(FacultyRequest request, Faculty faculty) {
        faculty.setName(request.getName().trim());
        faculty.setEmail(request.getEmail().trim().toLowerCase());
        faculty.setPhoneNumber(request.getPhoneNumber().trim());
        faculty.setDepartment(request.getDepartment().trim());
        faculty.setDesignation(request.getDesignation().trim());
        faculty.setQualification(request.getQualification().trim());
        faculty.setExperience(request.getExperience());
        faculty.setSpecialization(request.getSpecialization().trim());
    }
}
