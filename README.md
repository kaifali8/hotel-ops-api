# Hotel Ops API

A production-style hotel booking backend system built with Spring Boot.

## Tech Stack
- Java 21, Spring Boot 3
- Spring Security + JWT Authentication
- Hibernate / Spring Data JPA
- MySQL
- Docker

## Features
- JWT-based authentication & role-based access control
- Room management (CRUD, status, soft delete)
- Booking management with overlap conflict detection
- Pagination & filtering
- Global exception handling
- Standardized API responses

## Live API
Base URL: https://hotel-ops-api-w8dy.onrender.com  
Swagger UI: https://hotel-ops-api-w8dy.onrender.com/swagger-ui/index.html

## Run Locally
\`\`\`bash
git clone https://github.com/kaifali8/hotel-ops-api
cd hotel-ops-api
# Add application-dev.properties with your DB config
./mvnw spring-boot:run
\`\`\`
