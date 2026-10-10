# Database Setup & Verification Guide

This directory contains the database setup and SQL verification scripts for the **Faculty Management System**.

## 1. Quick Database Initialization

Run the following commands using the MySQL CLI or MySQL Workbench:

```bash
# 1. Log in to MySQL
mysql -u root -p

# 2. Run the schema creation script
source d:/Projects/faculty-management-system/database/schema.sql

# 3. (Optional) Run the seed data script
source d:/Projects/faculty-management-system/database/data.sql
```

Alternatively, from PowerShell:
```powershell
mysql -u root -p -e "source d:/Projects/faculty-management-system/database/schema.sql"
mysql -u root -p -e "source d:/Projects/faculty-management-system/database/data.sql"
```

> **Note**: The Spring Boot backend also auto-creates the schema using Hibernate/JPA (`spring.jpa.hibernate.ddl-auto=update`) and automatically seeds initial administrator credentials (`admin@university.edu` / `Admin@123`) and departments on first launch if the database is empty.

---

## 2. Inspecting Records Directly in MySQL

To verify that records are persisting properly into MySQL and not in temporary in-memory storage, run these queries:

```sql
USE faculty_db;

-- 1. View all faculty members
SELECT id, name, email, department, designation, experience FROM faculty;

-- 2. View faculty by department
SELECT department, COUNT(*) AS total_faculty 
FROM faculty 
GROUP BY department;

-- 3. View professor breakdown
SELECT designation, COUNT(*) AS count 
FROM faculty 
GROUP BY designation;

-- 4. View registered administrative users
SELECT id, email, full_name, role, created_at FROM users;

-- 5. View departments
SELECT * FROM departments;
```
