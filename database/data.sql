-- ==============================================================
-- FACULTY MANAGEMENT SYSTEM - INITIAL SEED DATA
-- Database Name: faculty_db
-- ==============================================================

USE faculty_db;

-- 1. Seed Departments
INSERT INTO departments (name, code, description) VALUES
('Computer Science & Engineering', 'CSE', 'Department of Computer Science and Engineering specializing in AI, Systems, and Software'),
('Information Technology', 'IT', 'Department of IT focused on Web Technologies, Cloud, and Cybersecurity'),
('Electronics & Communication', 'ECE', 'Department of ECE focused on VLSI, Embedded Systems, and Signal Processing'),
('Mechanical Engineering', 'MECH', 'Department of Mechanical Engineering with focus on Robotics and Design'),
('Electrical & Electronics', 'EEE', 'Department of EEE focused on Power Systems and Renewable Energy')
ON DUPLICATE KEY UPDATE name=name;

-- 2. Seed Faculty Records
INSERT INTO faculty (name, email, phone_number, department, designation, qualification, experience, specialization) VALUES
('Dr. Alan Turing', 'alan.turing@university.edu', '+1 (555) 019-2831', 'Computer Science & Engineering', 'Professor', 'Ph.D. in Computer Science', 16, 'Artificial Intelligence & Computability'),
('Dr. Ada Lovelace', 'ada.lovelace@university.edu', '+1 (555) 019-2832', 'Computer Science & Engineering', 'Professor', 'Ph.D. in Mathematics & CS', 14, 'Algorithms & Computational Theory'),
('Dr. Grace Hopper', 'grace.hopper@university.edu', '+1 (555) 019-2833', 'Information Technology', 'Professor', 'Ph.D. in Mathematics', 18, 'Compilers & Programming Languages'),
('Dr. Claude Shannon', 'claude.shannon@university.edu', '+1 (555) 019-2834', 'Electronics & Communication', 'Professor', 'Ph.D. in Electrical Engineering', 20, 'Information Theory & Signal Processing'),
('Dr. Barbara Liskov', 'barbara.liskov@university.edu', '+1 (555) 019-2835', 'Computer Science & Engineering', 'Associate Professor', 'Ph.D. in Computer Science', 11, 'Distributed Systems & Data Abstraction'),
('Dr. Donald Knuth', 'donald.knuth@university.edu', '+1 (555) 019-2836', 'Computer Science & Engineering', 'Professor', 'Ph.D. in Mathematics', 22, 'Algorithm Analysis & Typographic Systems'),
('Prof. Margaret Hamilton', 'margaret.hamilton@university.edu', '+1 (555) 019-2837', 'Information Technology', 'Associate Professor', 'M.S. in Mathematics & Systems', 12, 'Software Engineering & Fault-Tolerant Systems'),
('Dr. Tim Berners-Lee', 'tim.bernerslee@university.edu', '+1 (555) 019-2838', 'Information Technology', 'Associate Professor', 'Ph.D. in Computer Science', 13, 'Web Architectures & Semantic Networks'),
('Dr. Richard Feynman', 'richard.feynman@university.edu', '+1 (555) 019-2839', 'Mechanical Engineering', 'Professor', 'Ph.D. in Physics', 19, 'Quantum Mechanics & Nanotechnology'),
('Prof. Radia Perlman', 'radia.perlman@university.edu', '+1 (555) 019-2840', 'Electronics & Communication', 'Assistant Professor', 'Ph.D. in Computer Science', 6, 'Network Routing Protocols & Spanning Tree'),
('Prof. John von Neumann', 'john.vonneumann@university.edu', '+1 (555) 019-2841', 'Electrical & Electronics', 'Professor', 'Ph.D. in Mathematics & Physics', 21, 'Computer Architecture & Game Theory'),
('Prof. Linus Torvalds', 'linus.torvalds@university.edu', '+1 (555) 019-2842', 'Computer Science & Engineering', 'Assistant Professor', 'M.S. in Computer Science', 8, 'Operating Systems & Kernel Development')
ON DUPLICATE KEY UPDATE email=email;
