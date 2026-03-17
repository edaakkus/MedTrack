# 🏥 MedTrack – Web-Based Health Tracking System

🇹🇷 Türkçe açıklama:
MedTrack, kullanıcıların sağlık verilerini güvenli şekilde yönetmesini sağlayan web tabanlı bir uygulamadır.

🇬🇧 English:
MedTrack is a web-based health management system that allows users to securely manage their medical data.


## 🚀 Overview

MedTrack is a web-based health management system that enables users to securely track and manage their personal medical data, including prescriptions, health records, and user roles.

The system is built using **Spring Boot** with a layered architecture and implements **role-based access control (RBAC)** to ensure secure and scalable operations.

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security (Authentication & Authorization)
* **Database:** MySQL
* **ORM:** Hibernate (JPA)
* **API:** RESTful Services
* **Testing:** Postman

---

## ✨ Features

* User registration and authentication system
* Role-based authorization (Admin / User / etc.)
* CRUD operations for:

  * Health records
  * Prescriptions
  * Medications
* Secure password storage using **BCrypt**
* Global exception handling mechanism
* RESTful API design

---

## 🏗️ Architecture

The project follows a **layered architecture**:

* **Controller Layer** → Handles HTTP requests
* **Service Layer** → Business logic
* **Repository Layer** → Database operations
* **Entity Layer** → Data models

---

## 🗄️ Database Design

* Relational database with multiple interconnected entities
* Includes:

  * One-to-One
  * One-to-Many
  * Many-to-One
  * Many-to-Many relationships
* Join tables used for complex relationships
* EER diagram available in `/docs`

---

## 🔐 Security

* Implemented using **Spring Security**
* Role-based endpoint protection
* Password hashing with **BCrypt**
* Custom user and role management system

---

## 🔗 Sample API

### Get All Records

```http
GET /api/records
```

### Response

```json
{
  "id": 1,
  "title": "Blood Test",
  "description": "Routine check"
}
```

---

## ⚙️ Installation

```bash
git clone https://github.com/edaakkus/medtrack.git
cd medtrack
./mvnw spring-boot:run
```

Application will run on:

```
http://localhost:8080
```

---

## 📁 Project Structure

```
controller/
service/
repository/
entity/
config/
exception/
docs/
postman/
```

---

## 👥 Contributors

* **Eda Akkuş**
* Dilay Deveci

---

## 📌 Notes

This project was initially developed as a university coursework project and later improved based on feedback. It demonstrates backend development skills including **REST API design, database modeling, and secure authentication systems**.

---
