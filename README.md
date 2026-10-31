# 🏥 MedTrack – Web-Based Health Tracking & Medical Management System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg?logo=openjdk)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue.svg?logo=mysql)](https://www.mysql.com/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6-red.svg?logo=springsecurity)](https://spring.io/projects/spring-security)
[![Docker](https://img.shields.io/badge/Docker-Supported-2496ED.svg?logo=docker)](https://www.docker.com/)

MedTrack is an enterprise-grade personal health management platform and RESTful service built with **Spring Boot** and **Java 21**. It enables patients, doctors, and healthcare administrators to securely record, track, and visualize vital physiological metrics, medical records, and prescriptions with fine-grained role-based security.

---

## 📸 Preview

### Interactive Health Metric Dashboard
Patients and doctors can track weekly vitals (weight, pulse, sleep, hydration) with dynamic Chart.js visualizations:
<p align="center">
  <img src="medtrack/docs/dashboard-preview.png" alt="MedTrack Dashboard Preview" width="850"/>
</p>

### Database EER Diagram
Engineered using MySQL Workbench reverse engineering with explicit entity relationship mappings:
<p align="center">
  <img src="medtrack/docs/medtrack-eer-diagram.png" alt="MedTrack EER Diagram" width="850"/>
</p>

---

## ✨ Key Features

- **Role-Based Access Control (RBAC):** Hierarchical permissions tailored for `ADMIN`, `DOCTOR`, and `PATIENT` roles, secured by Spring Security and BCrypt password encryption.
- **Interactive Vitals Dashboard:** Web UI powered by Thymeleaf and Chart.js, rendering dynamic 7-day health trend analytics.
- **Explicit JPA / Hibernate Architecture:** Designed with native `EntityManager` and `TypedQuery` implementations (without relying on Spring Data repository magic) to ensure granular control over SQL queries, transaction lifecycles, and fetch strategies.
- **Relational Domain Model:** Implements all core JPA relationship types:
  - `@OneToOne` (Prescription ↔ Member)
  - `@ManyToOne` (Prescription → Doctor, HealthRecord → Member)
  - `@OneToMany` (Member → HealthRecords)
  - `@ManyToMany` (Prescription ↔ Medication, Member ↔ Role)
- **Centralized Exception Handling:** `@ControllerAdvice` providing standardized JSON error schemas across all REST controllers.
- **Production Observability:** Integrated Spring Boot Actuator monitoring (`/actuator/health`, `/actuator/info`).
- **Containerized Deployment:** Multi-stage `Dockerfile` and `docker-compose.yml` for instant orchestration with MySQL 8.4.

---

## 🛠️ Tech Stack

| Layer | Technologies |
|---|---|
| **Language & Runtime** | Java 21 (Eclipse Temurin) |
| **Framework** | Spring Boot 3.5.7 (Web, Security, Validation, Actuator, Thymeleaf, DevTools) |
| **Persistence & ORM** | Hibernate ORM 6.6, JPA 3.1, HikariCP, JDBC |
| **Database** | MySQL 8.0+ / MySQL 8.4 (Docker) |
| **Frontend / UI** | Thymeleaf, HTML5/CSS3, Chart.js |
| **DevOps & Containers** | Docker, Docker Compose |
| **Testing & Tooling** | JUnit 5, Spring Security Test, Postman |

---

## 🔐 Security & Access Control

Endpoints are secured using HTTP Basic Authentication with role-based method security:

| Resource | Endpoint | GET | POST | PUT / PATCH | DELETE | Allowed Roles |
|---|---|:---:|:---:|:---:|:---:|---|
| **Health Records** | `/records/**` | ✅ | ✅ | ✅ | ✅ (Admin/Doctor) | `ADMIN`, `DOCTOR`, `PATIENT` |
| **Members** | `/members/**` | ✅ | ✅ (Admin/Doctor) | ✅ (Admin/Doctor) | ✅ (Admin) | `ADMIN`, `DOCTOR`, `PATIENT` |
| **Doctors** | `/doctors/**` | ✅ | ✅ (Admin) | ✅ (Admin) | ✅ (Admin) | `ADMIN`, `DOCTOR`, `PATIENT` |
| **Prescriptions** | `/prescriptions/**` | ✅ | ✅ (Admin/Doctor) | ✅ (Admin/Doctor) | ✅ (Admin) | `ADMIN`, `DOCTOR`, `PATIENT` |
| **Medications** | `/medications/**` | ✅ | ✅ (Admin/Doctor) | ✅ (Admin/Doctor) | ✅ (Admin) | `ADMIN`, `DOCTOR`, `PATIENT` |
| **Roles / Users** | `/roles/**`, `/users/**`| ✅ | ✅ (Admin) | ✅ (Admin) | ✅ (Admin) | `ADMIN` |
| **Dashboard** | `/dashboard/{id}` | ✅ | — | — | — | All authenticated users |
| **Health Check** | `/actuator/health` | ✅ | — | — | — | Public (`permitAll`) |

### Default Test Credentials (Pre-seeded)

| Username / Email | Password | Role(s) | Description |
|---|---|---|---|
| `user` | `password` | `ADMIN`, `DOCTOR`, `PATIENT` | Master testing user |
| `admin@medtrack.com` | `admin123` | `ADMIN` | System administrator |
| `doctor@medtrack.com` | `doctor123` | `DOCTOR` | Healthcare specialist |
| `patient@medtrack.com` | `patient123` | `PATIENT` | Registered patient |

---

## 🚀 Getting Started

### Prerequisites
- **Java 21 JDK**
- **Maven** (or use the included `./mvnw` wrapper)
- **MySQL 8+** (or Docker Desktop)

---

### Option 1: Run with Docker (Recommended)

Run both the MySQL database and the Spring Boot application in isolated containers with a single command:

```bash
cd medtrack
docker compose up --build
```

The application will be available at:
👉 **`http://localhost:9090`**

---

### Option 2: Run Locally with Maven

1. **Create the MySQL Database:**
   ```sql
   CREATE DATABASE medtrack;
   ```

2. **Set your database credentials and start the application:**

   **Windows (PowerShell):**
   ```powershell
   cd medtrack
   $env:SPRING_DATASOURCE_PASSWORD="your_mysql_password"
   .\mvnw.cmd spring-boot:run
   ```

   **Linux / macOS (Bash):**
   ```bash
   cd medtrack
   export SPRING_DATASOURCE_PASSWORD="your_mysql_password"
   ./mvnw spring-boot:run
   ```

The application starts on port **`9090`**.

---

## 🧪 Testing the API

Once running, you can test the application using your browser or terminal:

### 1. Actuator Health Check (Public)
```bash
curl http://localhost:9090/actuator/health
```
```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP", "details": { "database": "MySQL" } },
    "diskSpace": { "status": "UP" }
  }
}
```

### 2. Fetch Health Records (Authenticated)
```bash
curl -u user:password http://localhost:9090/records
```
```json
[
  {
    "id": 1,
    "weight": 70.0,
    "pulse": 77,
    "waterIntake": 7,
    "sleepHours": 7,
    "recordDate": "2026-10-31"
  }
]
```

### 3. Open the Dashboard in Browser
Visit **`http://localhost:9090/dashboard/1`** and log in with `user` / `password`.

### 4. Postman Collection
An automated Postman collection covering all endpoints is available in:
📁 [`medtrack/postman/MedTrack.postman_collection.json`](medtrack/postman/MedTrack.postman_collection.json)

---

## 📁 Project Structure

```text
medtrack/
├── docs/                                # Technical documentation & EER diagrams
│   ├── dashboard-preview.png            # UI preview screenshot
│   ├── medtrack-eer-diagram.png         # Database EER diagram
│   ├── RELATIONSHIPS.md                 # Owning/Inverse side JPA documentation
│   └── SECURITY_ENDPOINTS.md            # Detailed RBAC matrix
├── postman/                             # API testing collections
│   └── MedTrack.postman_collection.json # Ready-to-import Postman collection
└── src/main/java/tr/edu/gumushane/medtrack/
    ├── config/                          # Security, JPA, & Data Initializer config
    ├── controller/                      # REST & MVC controllers (Thymeleaf)
    ├── entity/                          # JPA domain entities (Member, Record, Doctor, etc.)
    ├── exception/                       # Global exception handling & error responses
    ├── repository/                      # EntityManager-based DAO implementations
    └── service/                         # Business logic layer
```

---

## 👥 Contributors

- **Eda Akkuş** - [@edaakkus](https://github.com/edaakkus)
- **Dilay Deveci**

---

## 📜 License & Acknowledgments

This project was developed as an advanced software architecture and database design coursework project at **Gümüşhane University**. It demonstrates scalable backend engineering, clean RESTful patterns, database normalization, and Spring Security best practices.
