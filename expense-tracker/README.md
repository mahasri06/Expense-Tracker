# Expense Tracker

A simple, robust, and scalable Expense Tracker RESTful API built with **Spring Boot** and **Java 17**.

## Overview
This API allows users to easily track their expenses. It features adding, updating, retrieving, deleting, and filtering expenses. 
Built using a clean 3-tier architecture (Controller-Service-Repository) and leveraging **Spring Data JPA** for data persistence.

## Technologies Used
- **Java 17**
- **Spring Boot** (v4.0.3)
- **Spring WebMVC** (REST API)
- **Spring Data JPA** (Hibernate)
- **MySQL Database**
- **Lombok** (Boilerplate reduction)
- **Maven**
- **Logback** (SLF4J for logging)

## System Design

### Architecture
This project follows a standard **Monolithic Layered Architecture**:
1. **Controller Layer**: Exposes REST API endpoints (`/expenses`) and handles HTTP requests/responses, payload validation, and routes requests to the Service layer.
2. **Service Layer**: Contains the core business logic. Uses DTOs to encapsulate incoming data and mapping logic via `ExpenseMapper`. Contains logic like pagination and complex filtering logic.
3. **Repository Layer**: Responsible for DB interactions. Utilizes `ExpenseRepository` and `ExpenseSpecification` (for dynamic queries based on categories, amounts, dates).
4. **Exception Handling**: Global exception handling guarantees all exceptions return a standard API Response wrapper.

### Database Schema
**Entity:** `Expense`
- `id` (Primary Key, Auto-Increment)
- `title` (VARCHAR)
- `amount` (DECIMAL)
- `category` (VARCHAR)
- `date` (DATE)
- `description` (VARCHAR)

## How to Run

1. Clone or download the repository.
2. Configure your MySQL database settings in `src/main/resources/application.properties`.
3. Build the project using Maven: 
   ```bash
   ./mvnw clean compile
   ```
4. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Key Features
- Standardized API Responses with an `ApiResponse` wrapper class.
- Dynamic filtering (by date, amount, category).
- Pagination and Sorting.
- DTO (Data Transfer Object) pattern utilized for decoupled logic.
