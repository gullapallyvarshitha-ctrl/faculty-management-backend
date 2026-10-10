package com.faculty.service;

import com.faculty.dto.DashboardStats;
import com.faculty.model.Faculty;
import com.faculty.repository.DepartmentRepository;
import com.faculty.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public DashboardStats getDashboardStats() {
        List<Faculty> allFaculty = facultyRepository.findAll();

        long totalFaculty = allFaculty.size();
        long totalDepartments = departmentRepository.count();

        long totalProfessors = allFaculty.stream()
                .filter(f -> "Professor".equalsIgnoreCase(f.getDesignation()))
                .count();

        long totalAssociateProfessors = allFaculty.stream()
                .filter(f -> "Associate Professor".equalsIgnoreCase(f.getDesignation()))
                .count();

        long totalAssistantProfessors = allFaculty.stream()
                .filter(f -> "Assistant Professor".equalsIgnoreCase(f.getDesignation()))
                .count();

        double avgExp = allFaculty.isEmpty() ? 0.0 :
                allFaculty.stream()
                        .mapToInt(Faculty::getExperience)
                        .average()
                        .orElse(0.0);

        // Faculty count grouped by department
        Map<String, Long> facultyByDept = new HashMap<>();
        for (Faculty f : allFaculty) {
            String dept = f.getDepartment();
            facultyByDept.put(dept, facultyByDept.getOrDefault(dept, 0L) + 1);
        }

        List<Faculty> recentFaculty = facultyRepository.findTop5ByOrderByIdDesc();

        return new DashboardStats(
                totalFaculty,
                totalDepartments,
                totalProfessors,
                totalAssociateProfessors,
                totalAssistantProfessors,
                Math.round(avgExp * 10.0) / 10.0,
                facultyByDept,
                recentFaculty
        );
    }
}
