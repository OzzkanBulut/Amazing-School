https://github.com/user-attachments/assets/5fec9972-bc08-4c7c-8c77-62caa749db2a

## Overview

The **Amazing School Management Project** is a modern web application designed to streamline the management of educational institutions. Built with **Spring Boot** and **Thymeleaf**, this application serves as a comprehensive tool for handling various aspects of school administration, including student management, class scheduling, and faculty oversight.

### Key Highlights

- **Robust Backend**: Developed with **Spring Boot**, the backend is designed to be scalable and maintainable. It includes features like authentication, authorization, and role-based access control to ensure secure and efficient management of user data.
  
- **Dynamic Frontend**: Using **Thymeleaf**, the frontend is both interactive and user-friendly. The application offers a responsive design that adapts to various devices, ensuring a seamless experience for users on desktops, tablets, and smartphones.

- **Role-Based Access Control**: The system incorporates advanced security features to differentiate between anonymous users, students, and administrators. This ensures that users only have access to the functionalities appropriate to their roles.

- **Modular Architecture**: The application is organized into well-defined packages, each serving a specific purpose. This modular approach enhances code readability, ease of maintenance, and allows for efficient collaboration among developers.

- **Custom Error Handling**: It includes custom error pages for common issues like 404 Not Found and 403 Forbidden, providing users with a more professional and user-friendly experience.

The project aims to provide a reliable and flexible platform that can be customized to meet the needs of various educational institutions, making school management simpler and more efficient.

## Features

### Comprehensive Student Management

- **Student Profiles**: Create, view, update, and delete student profiles. Each profile includes personal information, enrollment details, and class assignments.
- **Class Enrollment**: Assign students to different classes and track their progress. Administrators can view and manage student enrollments with ease.
- **Course Enrollment**: Assign students to different courses and track their progress. Administrators can view and manage student enrollments with ease.

**While a student can be assigned to only one class, this limit does not exist for courses. A student can be assigned to multiple courses.**

### Class and Course Management

- **Class Creation**: Define and manage classes, including class names and assigned students.
- **Course Creation**: Define and manage courses, including course names,fees and assigned students.


### User Authentication and Authorization

- **Secure Login**: Implement secure authentication mechanisms, allowing users to log in with usernames and passwords.
- **Role-Based Access Control**: Differentiate between anonymous users, students, and administrators. Ensure that content and functionalities are displayed based on the user’s role.

### Responsive and User-Friendly Interface

- **Responsive Design**: The application features a responsive design that adjusts to different screen sizes, providing a consistent user experience across devices.
- **Interactive Elements**: Enhance user interaction with dynamic elements and real-time updates, ensuring a smooth and engaging experience.

### Data Validation and Error Handling

- **Input Validation**: Implement comprehensive validation rules to ensure that user input is accurate and meets the application’s requirements.
- **Error Pages**: Custom error pages for 404 Not Found, 403 Forbidden, and other common errors, improving user experience and reducing confusion.

### Additional Features

- **Auditing and Logging**: Track changes and log important events within the system for auditing purposes. This helps in maintaining data integrity and troubleshooting issues.

This project is designed to be a flexible and scalable solution for educational institutions, providing all the essential tools needed to manage students and classes effectively.

## Package Structure

![image](https://github.com/user-attachments/assets/d5cb4afd-8daa-40a6-b5e4-7ee12d7d1d52)


### 1. **Annotations**
   - **Purpose**: Contains custom annotations used throughout the application for various purposes.
   - **Examples**: `@FieldsValueMatch`, `@Passwordvalidator`

### 2. **Aspects**
   - **Purpose**: Contains aspect-oriented programming (AOP) aspects for cross-cutting concerns.
   - **Examples**: `LoggerAspect`

### 3. **Auditing**
   - **Purpose**: Contains classes related to auditing and tracking changes in the application.
   - **Examples**: `AuditAwareImpl`

### 4. **Config**
   - **Purpose**: Configuration classes for setting up application settings, security, and other configurations.
   - **Examples**: `ProjectSecurityConfig`, `WebConfig`

### 5. **Controller**
   - **Purpose**: Contains the controllers responsible for handling HTTP requests and returning responses.
   - **Examples**: `StudentController`, `AdminController`, `LoginController`

### 6. **Model**
   - **Purpose**: Contains the data model classes and entities used in the application.
   - **Examples**: `Student`, `AmazingClass`, `Contact`

### 7. **Repository**
   - **Purpose**: Contains repository interfaces for data access and CRUD operations.
   - **Examples**: `StudentRepository`, `PersonRepository`, `ContactRepository`

### 8. **Rest**
   - **Purpose**: Contains RESTful API controllers for exposing data via HTTP endpoints.
   - **Examples**: `ContactRestController`, `GlobalExceptionRestController`

### 9. **Security**
   - **Purpose**: Contains security-related components such as authentication, authorization, and user management.
   - **Examples**: `BCryptPasswordEncoder`

### 10. **Service**
   - **Purpose**: Contains service classes that contain business logic and interact with repositories.
   - **Examples**: `ContactService`, `PersonService`

### 11. **Utils**
   - **Purpose**: Contains utility classes and helper functions used across the application.
   - **Examples**: `AmazingSchoolConstants`

### 12. **Validation**
   - **Purpose**: Contains classes and methods for validating user input and ensuring data integrity.
   - **Examples**: `FieldsValueMatchValidator`, `PasswordStrengthValidator`

## Getting Started

### Prerequisites
- **Java 21**
- **Maven** (for dependency management and building the project)
- A web browser (for accessing the application)
- **MySQL** 

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/OzzkanBulut/Amazing-School.git
   cd Amazing-School

2. **Set Up the Database**
   Configure the application.properties file with your database credentials:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/amazingschool
   spring.datasource.username=Your username
   spring.datasource.password=Your password
   spring.jpa.show-sql=true

3. **Run and Access the Application**

### Usage

**Administrator Login**

1. **Navigate to http://localhost:8080/login.**
   
2. Use the default administrator credentials (or create a new admin user if needed)
 
   - Username: admin@gmail.com
   - Password: admin123

### Managing Students, Classes, and Courses
- Students: Navigate to the Students section to add, edit, or delete student records. Assign students to classes and view their profiles.
- Classes: Go to the Classes section to create and manage class details and assignments.
- Courses: Go to the Courses section to create and manage course details and assignments

### Screenshots

![image](https://github.com/user-attachments/assets/86166138-06f4-4c26-9527-3896f62d8a25)
**Courses**


![image](https://github.com/user-attachments/assets/81a11ecd-0c42-43c7-a17a-1ab92b22ed14)
**Admin Dashboard**


![image](https://github.com/user-attachments/assets/4c4c3ac5-0ccb-4c5b-ae95-2a86cb658ebd)
**Student Dashboard**


![image](https://github.com/user-attachments/assets/9bc6dc7f-9b5d-4330-b3dd-73ae8eb350de)
**Enrolled Courses**


![image](https://github.com/user-attachments/assets/0c8bd7e5-0144-42e7-be88-d7a748698110)
**Contact Messages**


![image](https://github.com/user-attachments/assets/192e6baf-12e9-46c5-9715-4dc27323afea)
**All Students**


### Contact

For more information,questions or feedback, you can reach out:
- Email: ozkanb66@gmail.com
- Linkedin: https://www.linkedin.com/in/ozkan-bulut/
- Github: https://github.com/OzzkanBulut




