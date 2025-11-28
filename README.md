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

## Unit Testing with JUnit & Mockito

This project now includes a full suite of automated unit tests using JUnit 5, Mockito, MockMvc, and Spring Boot Test.
The tests cover the repository, service, and controller layers to ensure the reliability of all CRUD operations and search functionality.

You can run all tests using:

```bash
mvn test
```

---

## Maven Build Improvements (Suppressing JVM Warnings)

The pom.xml has been updated to include additional Maven plugins that eliminate console warnings such as:

- Sharing is only supported for boot loader classes
- Dynamic loading of agents will be disallowed by default in a future release

The following plugins were added:

```mvn
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <version>3.9.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>properties</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.5.4</version>
            <configuration>
                <argLine>@{argLine} -Xshare:off -javaagent:${org.mockito:mockito-core:jar}</argLine>
            </configuration>
        </plugin>
    </plugins>
</build>

```

These changes ensure cleaner test execution and better compatibility with future JVM versions.

---

## Future improvements

- Add authentication and role-based access
- Add pagination and sorting
- Add search functionality
- Add integration tests with Testcontainers

## Purpose
This project is part of a personal portfolio to strengthen programming fundamentals in Java and develop real, practical applications.