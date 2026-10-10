package com.faculty.config;

import com.faculty.model.Department;
import com.faculty.model.Faculty;
import com.faculty.model.User;
import com.faculty.repository.DepartmentRepository;
import com.faculty.repository.FacultyRepository;
import com.faculty.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedAdminUser();
        seedDepartments();
        seedFaculty();
    }

    private void seedAdminUser() {
        if (!userRepository.existsByEmailIgnoreCase("admin@university.edu")) {
            User admin = new User(
                    "admin@university.edu",
                    passwordEncoder.encode("Admin@123"),
                    "University Administrator",
                    "ROLE_ADMIN"
            );
            userRepository.save(admin);
            log.info("Initialized default administrator: admin@university.edu / Admin@123");
        }
    }

    private void seedDepartments() {
        if (departmentRepository.count() == 0) {
            List<Department> initialDepartments = List.of(
                    new Department("Computer Science & Engineering", "CSE", "Department of Computer Science & Engineering"),
                    new Department("Information Technology", "IT", "Department of Information Technology & Cloud Systems"),
                    new Department("Electronics & Communication", "ECE", "Department of Electronics & Communication"),
                    new Department("Mechanical Engineering", "MECH", "Department of Mechanical Engineering & Robotics"),
                    new Department("Electrical & Electronics", "EEE", "Department of Electrical & Electronics Engineering")
            );
            departmentRepository.saveAll(initialDepartments);
            log.info("Initialized default departments (5 records)");
        }
    }

    private void seedFaculty() {
        if (facultyRepository.count() == 0) {
            List<Faculty> initialFaculty = List.of(
                    new Faculty("Dr. Alan Turing", "alan.turing@university.edu", "+1 (555) 019-2831",
                            "Computer Science & Engineering", "Professor", "Ph.D. in Computer Science", 16, "Artificial Intelligence & Computability"),
                    new Faculty("Dr. Ada Lovelace", "ada.lovelace@university.edu", "+1 (555) 019-2832",
                            "Computer Science & Engineering", "Professor", "Ph.D. in Mathematics & CS", 14, "Algorithms & Computational Theory"),
                    new Faculty("Dr. Grace Hopper", "grace.hopper@university.edu", "+1 (555) 019-2833",
                            "Information Technology", "Professor", "Ph.D. in Mathematics", 18, "Compilers & Programming Languages"),
                    new Faculty("Dr. Claude Shannon", "claude.shannon@university.edu", "+1 (555) 019-2834",
                            "Electronics & Communication", "Professor", "Ph.D. in Electrical Engineering", 20, "Information Theory & Signal Processing"),
                    new Faculty("Dr. Barbara Liskov", "barbara.liskov@university.edu", "+1 (555) 019-2835",
                            "Computer Science & Engineering", "Associate Professor", "Ph.D. in Computer Science", 11, "Distributed Systems & Data Abstraction"),
                    new Faculty("Dr. Donald Knuth", "donald.knuth@university.edu", "+1 (555) 019-2836",
                            "Computer Science & Engineering", "Professor", "Ph.D. in Mathematics", 22, "Algorithm Analysis & Typographic Systems"),
                    new Faculty("Prof. Margaret Hamilton", "margaret.hamilton@university.edu", "+1 (555) 019-2837",
                            "Information Technology", "Associate Professor", "M.S. in Mathematics & Systems", 12, "Software Engineering & Fault-Tolerant Systems"),
                    new Faculty("Dr. Tim Berners-Lee", "tim.bernerslee@university.edu", "+1 (555) 019-2838",
                            "Information Technology", "Associate Professor", "Ph.D. in Computer Science", 13, "Web Architectures & Semantic Networks"),
                    new Faculty("Dr. Richard Feynman", "richard.feynman@university.edu", "+1 (555) 019-2839",
                            "Mechanical Engineering", "Professor", "Ph.D. in Physics", 19, "Quantum Mechanics & Nanotechnology"),
                    new Faculty("Prof. Radia Perlman", "radia.perlman@university.edu", "+1 (555) 019-2840",
                            "Electronics & Communication", "Assistant Professor", "Ph.D. in Computer Science", 6, "Network Routing Protocols & Spanning Tree"),
                    new Faculty("Prof. John von Neumann", "john.vonneumann@university.edu", "+1 (555) 019-2841",
                            "Electrical & Electronics", "Professor", "Ph.D. in Mathematics & Physics", 21, "Computer Architecture & Game Theory"),
                    new Faculty("Prof. Linus Torvalds", "linus.torvalds@university.edu", "+1 (555) 019-2842",
                            "Computer Science & Engineering", "Assistant Professor", "M.S. in Computer Science", 8, "Operating Systems & Kernel Development")
            );
            facultyRepository.saveAll(initialFaculty);
            log.info("Initialized default faculty records (12 records)");
        }
    }
}
