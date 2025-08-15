# Job Application Management API (Spring Boot)

This is a RESTful API built using Spring Boot for managing job applications.

## 🚧 Project Status

🔧 This project is currently under active development. More features will be added in the upcoming versions.

## 🔑 Features (Completed)
- User Registration & Login with JWT Auth ✅
- Role-based access (Admin/User) ✅
- Company & Job APIs (Create, Read, Update, Delete) ✅
- Job Application endpoints ✅
- Pagination for Users, Jobs, and Companies ✅
- Searching in Jobs by title, location, and company name ✅

## 🔑 Features in Progress / Planned
- User Logout with JWT (Token invalidation mechanism)
- Filtering for Jobs (e.g., salary range, location, company type)
- Job Application Endpoints (Apply to jobs, view applications)

## 🛠 Tech Stack
- ☕ Java 17
- 🌱 Spring Boot
- 🔐 Spring Security with JWT
- 🗄  Spring Data JPA
- 🛢  MySQL
- 🧪 Postman (for API testing)
- 📦 Maven (build tool)

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
  - **src/main/resources** – Application properties, static files
  - **src/test/java** – Test classes
  - **src/test/resources** – Test resources
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



## 📌 API Endpoints

| Method | Endpoint                  | Description                           | Role       |
|--------|---------------------------|---------------------------------------|------------|
| POST   | `/auth/register`          | Register a new user                   | Public     |
| POST   | `/auth/login`             | Login and get JWT token               | Public     |
| GET    | `/api/jobs`               | List all jobs (with pagination)       | USER/ADMIN |
| GET    | `/api/jobs/search`        | Search jobs by title/location/company | USER/ADMIN |
| POST   | `/api/companies`          | Create new company                    | ADMIN      |
| PUT    | `/ap/companies/{id}`      | Update company                        | ADMIN      |
| DELETE | `api//companies/{id}`     | Delete company                        | ADMIN      |
| POST   | `/api/jobs`               | Create a new job                      | ADMIN      |



---
