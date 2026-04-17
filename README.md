# Job Application Management API (Spring Boot)

A production-style RESTful API built using Spring Boot that simulates a real-world job portal backend system. It supports authentication, job management, and job application workflows.

## 🚧 Project Status

🔧 Actively under development — continuously improving with real-world features.

## 🔑 Features (Completed)
- 🔐 User Registration & Login using JWT Authentication ✅
- 🛡 Role-Based Access Control (Admin/User) ✅
- 🏢💼 Company & Job APIs (Create, Read, Update, Delete) ✅
- 📄 Job Application System (Apply to jobs with resume upload) ✅
- 📂 File Upload Handling using MultipartFile ✅
- 🚫 Duplicate Application Prevention ✅
- 📑 Pagination for Users, Jobs, and Companies ✅
- 🔍 Job Search (by title, location, company name) ✅
- 🔄 DTO-based API responses (clean & secure design)
  

## 🚧 Upcoming Features
- 📧 Email Notification System (User + Admin alerts)
- 🔓 Logout with JWT Token Invalidation
- J🎯 Advanced Job Filtering (salary range, location, company type)
- 📥 View & Manage Job Applications (Admin Panel)

## 🛠 Tech Stack
- ☕ Java 17
- 🌱 Spring Boot
- 🔐 Spring Security + JWT
- 🗄  Spring Data JPA
- 🛢  MySQL
- 🧪 Postman (for API testing)
- 📦 Maven (build tool)


## 🧠 Key Highlights
- Implemented secure authentication & authorization using JWT
- Designed clean layered architecture (Controller → Service → Repository)
- Used DTO pattern to prevent sensitive data exposure
- Implemented file upload system for resumes
- Applied real-world business logic (duplicate application prevention)
- Followed Git branching strategy (feature-based development)


## 📌 Notes
- Default role during registration: ROLE_USER
- Admin endpoints are restricted to users with ROLE_ADMIN
- All endpoints require JWT token except /register and /login

  ## 📁 Project Structure
 
 - **jobApplication**
  - **src/main/java**
    - `com.tech.jobApp`
      - **config** – Application & Security configurations
      - **controller** – REST Controllers
      - **dto.request** – Request DTOs
      - **dto.response** – Response DTOs
      - **filter** – Filters (e.g., JWT filter)
      - **mapper** – Entity ↔ DTO mapping logic
      - **model** – Entity classes
      - **repository** – JPA repositories
      - **service** – Service interfaces
      - **service.impl** – Service implementations
  - **src/main/resources** – Configuration files
  - **target** – Build output



## ⚙️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/Dishikamewada-01/job-application-api.git
cd job-application-api
```

### 2. Open in IDE
```
Import the project as a Maven Project in Eclipse or IntelliJ IDEA.
Wait until all dependencies are downloaded by the IDE.
```

### 3. Configure Database
Update the following in your application.properties file:
```
spring.datasource.url=jdbc:mysql://localhost:3306/job_app
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Run the Application
```
Run the main class JobApplication.java
Your Spring Boot app will be available at: http://localhost:8080
```
### 5. Run Tests
```
mvn test

Unit tests are written using JUnit 5 and Mockito for the service layer.
This ensures that the business logic is tested independently of the database layer.
```



## 📌 API Endpoints

| Method | Endpoint                         | Description                           | Role       |
|--------|--------------------------------- |---------------------------------------|------------|
| POST   | `/auth/register`                 | Register a new user                   | Public     |
| POST   | `/auth/login`                    | Login and get JWT token               | Public     |
| GET    | `/api/jobs`                      | List all jobs (with pagination)       | USER/ADMIN |
| GET    | `/api/jobs/search`               | Search jobs by title/location/company | USER/ADMIN |
| POST   | `/api/companies`                 | Create new company                    | ADMIN      |
| PUT    | `/ap/companies/{id}`             | Update company                        | ADMIN      |
| DELETE | `api//companies/{id}`            | Delete company                        | ADMIN      |
| POST   | `/api/jobs`                      | Create a new job                      | ADMIN      |
| POST   | `/api/applications/apply/{jobId}`| Apply to Job                          | USER       |


---
