# Faculty Management System

A production-grade, full-stack **Faculty Management System** built with **React + Vite** and **Java Spring Boot + MySQL**, designed for university academic administration.

---

## 🏛️ System Architecture

```
faculty-management-system/
├── frontend/             # React 19 + Vite + Modern Administration UI
│   ├── src/
│   │   ├── components/   # Dashboard, FacultyList, Modals, Navbar, Toast
│   │   ├── api.js        # Centralized REST API client (VITE_API_BASE_URL)
│   │   ├── App.jsx       # State management & tab orchestration
│   │   ├── App.css       # University blue-and-white theme
│   │   └── main.jsx
│   ├── .env.example
│   └── package.json
├── backend/              # Spring Boot 3 + Maven REST API
│   ├── src/main/java/com/faculty/
│   │   ├── config/       # Seed DataInitializer (Admin + Departments + Faculty)
│   │   ├── controller/   # REST Controllers (Auth, Faculty, Dept, Dashboard)
│   │   ├── dto/          # Data Transfer Objects with Bean Validation
│   │   ├── exception/    # Global Exception Handler (400, 401, 404, 409)
│   │   ├── model/        # JPA Entities (Faculty, Department, User)
│   │   ├── repository/   # Spring Data JPA Repositories
│   │   ├── security/     # Spring Security 6, JWT Filter, BCrypt
│   │   └── service/      # Business logic layer
│   ├── src/main/resources/application.properties
│   ├── .env.example
│   └── pom.xml
├── database/             # Database DDL, Seed Scripts & Verification Guide
│   ├── schema.sql        # MySQL table definitions
│   ├── data.sql          # Initial university dataset
│   └── README.md         # Database verification instructions
└── README.md
```

---

## 🚀 Key Features

### 1. Executive Academic Dashboard
- Real-time statistics: Total Faculty, Total Departments, Full Professors, Associate Professors, Assistant Professors.
- Academic experience calculation (average years across university).
- Departmental distribution progress bars with live percentages.
- Recent appointments / faculty table with fast profile view.

### 2. Faculty Record Management (Complete CRUD)
- **Add Faculty**: Comprehensive modal with real-time field validation.
- **View Dossier**: Complete modal profile with avatar, contact channels, credentials, and research specialization.
- **Edit Faculty**: In-place updates with duplicate email validation.
- **Delete Faculty**: Protected removal with confirmation dialog.
- **Search & Filter**: Multi-attribute search (by name, email, department, designation, specialization) with instant reset.

### 3. Department Management
- Department directory with codes (e.g. `CSE`, `IT`, `ECE`), titles, and mission descriptions.
- Dynamic department registration.

### 4. Enterprise Security & Role-Based Authorization
- **BCrypt Password Hashing** for credentials.
- **Stateless JWT Tokens** for authenticated requests.
- **Protected Mutations**: Adding, editing, and deleting records requires authenticated administrator session.
- **Public Read Access**: Students and guests can view the dashboard and faculty directory.
- Pre-seeded Administrator:
  - **Email**: `admin@university.edu`
  - **Password**: `Admin@123`

---

## 🛠️ Tech Stack & Prerequisites

| Layer | Technology | Version |
|---|---|---|
| **Frontend** | React + Vite + JavaScript | React 19, Vite 8 |
| **Backend** | Java + Spring Boot + Maven | Java 17, Spring Boot 3.3.4 |
| **Database** | MySQL Server | MySQL 8.0+ |
| **Icons** | Lucide React | v1.55 |
| **Security** | Spring Security + JWT + BCrypt | JJWT 0.11.5 |

---

## ⚙️ Quick Start Guide

### Step 1: Configure MySQL Database

1. Ensure MySQL service is running on port 3306.
2. Initialize `faculty_db` by running the schema script:
   ```bash
   mysql -u root -p -e "source d:/Projects/faculty-management-system/database/schema.sql"
   ```
   *(Note: Spring Boot's JPA will also auto-create and seed tables on first run if configured).*

### Step 2: Run the Spring Boot Backend

1. Navigate to the backend directory:
   ```bash
   cd d:\Projects\faculty-management-system\backend
   ```
2. Set your MySQL password in environment or `.env`:
   ```powershell
   $env:DB_PASSWORD="your_mysql_password"
   mvn spring-boot:run
   ```
3. The backend starts on `http://localhost:8080`.

### Step 3: Run the React Frontend

1. Open a new terminal and navigate to `frontend`:
   ```bash
   cd d:\Projects\faculty-management-system\frontend
   ```
2. Start the Vite development server:
   ```bash
   npm run dev
   ```
3. Open your browser at `http://localhost:5173`.

---

## 📡 REST API Reference

| Method | Endpoint | Description | Security |
|---|---|---|---|
| `POST` | `/api/auth/login` | Administrator login (returns JWT) | Public |
| `GET` | `/api/auth/me` | Current authenticated user details | Authenticated |
| `GET` | `/api/dashboard/stats` | Executive dashboard metrics | Public |
| `GET` | `/api/faculty` | List faculty (`?query=`, `?department=`, `?designation=`) | Public |
| `GET` | `/api/faculty/{id}` | Get faculty by ID | Public |
| `POST` | `/api/faculty` | Create new faculty member | **Admin Token** |
| `PUT` | `/api/faculty/{id}` | Update existing faculty | **Admin Token** |
| `DELETE` | `/api/faculty/{id}` | Delete faculty record | **Admin Token** |
| `GET` | `/api/departments` | List all departments | Public |
| `POST` | `/api/departments` | Register new department | **Admin Token** |
| `DELETE` | `/api/departments/{id}`| Delete department | **Admin Token** |

---

## 🧪 Testing the Application

### 1. Verification of Persistent Records in MySQL
Run the verification queries from `database/README.md`:
```sql
USE faculty_db;
SELECT id, name, email, department, designation, experience FROM faculty;
```

### 2. Frontend Build Verification
```bash
cd frontend
npm run build
```

### 3. Backend Compilation Verification
```bash
cd backend
mvn clean test
```

---

## 🌐 Deployment Instructions

### Frontend (Vercel)
1. Push the `frontend` folder or mono-repo to GitHub.
2. In Vercel, import the repository and set Root Directory to `frontend`.
3. Set the Environment Variable:
   - `VITE_API_BASE_URL` = `https://your-production-backend-url.com/api`
4. Deploy!

### Backend (Render / Railway / AWS / Azure)
1. Provide a managed MySQL instance (e.g. AWS RDS, Railway MySQL, PlanetScale, or Aiven).
2. Set environment variables on the Java host:
   - `DB_URL` = `jdbc:mysql://<db-host>:3306/faculty_db?useSSL=true`
   - `DB_USERNAME` = `<db-username>`
   - `DB_PASSWORD` = `<db-password>`
   - `JWT_SECRET` = `<custom-64-character-secret>`
