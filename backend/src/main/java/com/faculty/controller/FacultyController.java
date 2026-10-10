package com.faculty.controller;

import com.faculty.dto.FacultyRequest;
import com.faculty.model.Faculty;
import com.faculty.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String designation) {
        List<Faculty> facultyList = facultyService.getAllFaculty(query, department, designation);
        return ResponseEntity.ok(facultyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@Valid @RequestBody FacultyRequest request) {
        Faculty created = facultyService.createFaculty(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(
            @PathVariable Long id,
            @Valid @RequestBody FacultyRequest request) {
        Faculty updated = facultyService.updateFaculty(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Faculty record deleted successfully");
        return ResponseEntity.ok(response);
    }
}
