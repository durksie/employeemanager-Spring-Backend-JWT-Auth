# Project Overview
Employee Manager using Spring Boot REST API application that manages employees using full CRUD
(Create, Read, Update, Delete) operations. It uses MySQL as the database and Spring Data JPA for data
persistence.

The application now also includes user authentication and authorization, enabling secure access control to API endpoints. This backend service can be integrated with web or mobile frontends.

## This backend can be integrated with web or mobile frontends.
# Features
* Create new employees
* View all employees
* View employee by ID
* Update employee details
* Delete employees
* MySQL database integration
* RESTful API design
* User authentication (login system)
* Role-based authorization (secure endpoints)

# Technologies Used
*Java 25
*Spring Boot
*Spring Data JPA
*MySQL
*Maven
*Lombok (Optional)
*Postman (Testing Api)

# Prerequisites
## Make sure you have installed:
*Java 25+
*Maven
*MySQL Server
*IDE (IntelliJ )
## Check versions:
*java --version
*mvn --version
*mysql --version
# Database Setup (MySQL)
## Login to MySQL
* mysql -u root -p
# Create Database
* CREATE DATABASE employeemanager;
# Application Configuration
Edit file for database :

## src/main/resources/application.properties
* Add the following configuration:
# MySQL Configuration
* spring.datasource.url=jdbc:mysql://localhost:3306/employeemanager
* spring.datasource.username=root
* spring.datasource.password=
* spring.jpa.show-sql=true
* spring.jpa.hibernate.ddl-auto=update
* spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
# Replace the password field with your MySQL password if needed.
## Running the Application
* Using Maven
= mvn spring-boot:run
* Using IDE(IntelliJ)
Open the project
* Run EmployeeManagerApplication.java
You should see:
Tomcat started on port 8080
 
#  API Endpoints
## Create Employee
Base URL: http://localhost:8080/api/employees
POST /api/employees
{
"name": "John Doe",
"email": "john@gmail.com",
"department": "IT",
"salary": 15000
}
## Get All Employees
GET /api/employees
🔍 Get Employee By ID
GET /api/employees/{id}
✏️ Update Employee
PUT /api/employees/{id}
{
"name": "John Smith",
"email": "johnsmith@gmail.com",
"department": "HR",
"salary": 18000
}
## Delete Employee
DELETE /api/employees/{id}

# Security Notes

* Authentication is required for protected endpoints.

* Users must log in to access secured routes.

* Role-based permissions determine which actions users can perform.

* Passwords are encrypted before storage for security.

# Future Improvements

* JWT authentication

* Pagination & sorting

* Search/filter endpoints

* Docker containerization

* Frontend dashboard
