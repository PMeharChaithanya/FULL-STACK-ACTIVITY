# Activity 3: Demo REST Service in Spring Boot

**Reg No:** RA2311003011556

## Task
Run the Demo REST service project on the local machine and share a screenshot of the browser output.

## Project
- `DemoApplication.java` – main class, also a `@RestController` with `GET /` returning a Hello World message.
- `StudentController.java` – `GET /students` returns a list of `Student` objects as JSON.
- `Student.java` – model with `rollno`, `name`, `cgpa`.

## What I did
- Ran the project with Maven.
- Added my Reg No to the Hello World message and the startup log.

## How to run
```bash
cd demo_2
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8083
```

## Output
| URL | Response |
|---|---|
| `http://localhost:8083/` | `Hello World from Spring Boot - Reg No: RA2311003011556` |
| `http://localhost:8083/students` | `[{"cgpa":9.8,"name":"Raja","rollno":1},{"cgpa":8.7,"name":"Ramu","rollno":2}]` |
