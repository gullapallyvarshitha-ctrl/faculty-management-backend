package com.faculty.dto;

import com.faculty.model.Faculty;
import java.util.List;
import java.util.Map;

public class DashboardStats {

    private long totalFaculty;
    private long totalDepartments;
    private long totalProfessors;
    private long totalAssociateProfessors;
    private long totalAssistantProfessors;
    private double averageExperience;
    private Map<String, Long> facultyByDepartment;
    private List<Faculty> recentFaculty;

    public DashboardStats() {
    }

    public DashboardStats(long totalFaculty, long totalDepartments, long totalProfessors,
                          long totalAssociateProfessors, long totalAssistantProfessors,
                          double averageExperience,
                          Map<String, Long> facultyByDepartment, List<Faculty> recentFaculty) {
        this.totalFaculty = totalFaculty;
        this.totalDepartments = totalDepartments;
        this.totalProfessors = totalProfessors;
        this.totalAssociateProfessors = totalAssociateProfessors;
        this.totalAssistantProfessors = totalAssistantProfessors;
        this.averageExperience = averageExperience;
        this.facultyByDepartment = facultyByDepartment;
        this.recentFaculty = recentFaculty;
    }

    public long getTotalFaculty() {
        return totalFaculty;
    }

    public void setTotalFaculty(long totalFaculty) {
        this.totalFaculty = totalFaculty;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public long getTotalProfessors() {
        return totalProfessors;
    }

    public void setTotalProfessors(long totalProfessors) {
        this.totalProfessors = totalProfessors;
    }

    public long getTotalAssociateProfessors() {
        return totalAssociateProfessors;
    }

    public void setTotalAssociateProfessors(long totalAssociateProfessors) {
        this.totalAssociateProfessors = totalAssociateProfessors;
    }

    public long getTotalAssistantProfessors() {
        return totalAssistantProfessors;
    }

    public void setTotalAssistantProfessors(long totalAssistantProfessors) {
        this.totalAssistantProfessors = totalAssistantProfessors;
    }

    public double getAverageExperience() {
        return averageExperience;
    }

    public void setAverageExperience(double averageExperience) {
        this.averageExperience = averageExperience;
    }

    public Map<String, Long> getFacultyByDepartment() {
        return facultyByDepartment;
    }

    public void setFacultyByDepartment(Map<String, Long> facultyByDepartment) {
        this.facultyByDepartment = facultyByDepartment;
    }

    public List<Faculty> getRecentFaculty() {
        return recentFaculty;
    }

    public void setRecentFaculty(List<Faculty> recentFaculty) {
        this.recentFaculty = recentFaculty;
    }
}
