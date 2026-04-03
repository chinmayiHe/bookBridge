BookBridge - Book Exchange Platform

Project Overview:
    BookBridge is a Spring Boot backend application that allows users to exchange books with each other.
    It manages users, books, and exchange requests with a structured workflow.

Features
- User Registration
- Add Books under a User
- Search Books by title or author
- Request a Book
- Approve / Reject Requests
- Delete Users, Books, and Requests
- Status-based workflow management
  
Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

Workflow
User registers
User adds books → status = AVAILABLE
Another user requests book → status = REQUESTED
Owner:
Approves → Book = EXCHANGED
Rejects → Book = AVAILABLE
Deleting request → Book becomes AVAILABLE again

Key Concepts Implemented
- Layered Architecture (Controller → Service → Repository)
- Entity Relationships (One-to-Many, Many-to-One)
- Foreign Key Handling
- State Transition Logic
- Exception Handling using try-catch
- Clean API Response Design

How to Run
- Clone the repository
- Configure PostgreSQL in application.properties
- Run the Spring Boot application
- Test APIs using Postman
  
Conclusion:
    This project demonstrates backend development skills including REST API design, database management, and real-world business logic implementation.
