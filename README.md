# Notes API
A simple REST API built with **Spring Boot**, **JPA**, and **H2 Database**.  
The project includes full CRUD operations, validation, H2 console access, and disabled Spring Security for easier local development.

---

## Features

- Create, read, update, and delete notes
- Input validation using Spring Validation
- In-memory H2 database
- H2 web console enabled
- JPA/Hibernate automatic schema generation
- Clean REST architecture
- Spring Security disabled for easier testing (can be enabled later)

---

##  Technologies Used

- **Java 23**
- **Spring Boot 3.5.0**
- **Spring Web MVC**
- **Spring Data JPA**
- **H2 Database**
- **JPA/Hibernate**
- **Validation API**

---

## Project Structure

```md
src/main/java/com/david/notes/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── exception/
└── NotesApiApplication.java
```

---

## Running the Project

1. Run with Maven
```bash
mvn spring-boot:run
```

2. Run the JAR
```bash
mvn clean package
java -jar target/notes-api-0.0.1-SNAPSHOT.jar
```

---

## Endpoints

| Method | Endpoint          | Description       |
| ------ | ----------------- | ----------------- |
| GET    | `/api/notes`      | Get all notes     |
| GET    | `/api/notes/{id}` | Get note by ID    |
| POST   | `/api/notes`      | Create a new note |
| PUT    | `/api/notes/{id}` | Update a note     |
| DELETE | `/api/notes/{id}` | Delete a note     |


---

## Future improvements

- Add authentication and role-based access
- Add pagination and sorting
- Add search functionality
- Add integration tests with Testcontainers

## Purpose
This project is part of a personal portfolio to strengthen programming fundamentals in Java and develop real, practical applications.