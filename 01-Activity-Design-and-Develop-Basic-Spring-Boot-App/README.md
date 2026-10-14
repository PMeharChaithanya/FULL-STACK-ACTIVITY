# Activity 1: Design and Develop a Basic Spring Boot Application

**Reg No:** RA2311003011556
**Application:** Library Management System

## Task
Choose any real-world domain and build a basic Spring Boot application that uses:
- Java, Spring Boot and Maven, runnable as a Spring Boot application
- A skeleton web application with at least one REST endpoint
- Spring Boot auto-configuration (no manual configuration of what Boot can configure)
- Three-layer architecture: Controller → Service → Repository
- At least 3 domain entities
- At least 2 business rules in the Service layer (not in the Controller)
- At least 3 test cases (input, expected output, actual output, PASS/FAIL)
- At least one automated JUnit / Spring Boot test
- A clear request flow: Client → Controller → Service → Repository → Data

The full question is in `Activity-unit4.docx`, and the complete written answer (architecture diagram, code, test cases, outputs, auto-configuration explanation) is in **`Activity-unit4-Answer.docx`**.

## Problem description
A library needs to keep a catalogue of books, register members, and track which member borrowed which book. Books must not be issued when no copies are left, members must not borrow too many books, and late returns must be fined.

## Architecture
```
Client (Browser / curl / Postman)
        │  HTTP (JSON)
        ▼
LibraryController   (@RestController)  – Presentation layer
        ▼
LibraryService      (@Service)         – Business layer (rules, @Transactional)
        ▼
Book/Member/LoanRepository (JpaRepository) – Data access layer
        ▼
H2 in-memory database (auto-configured)
```

## Domain model
| Entity | Attributes |
|---|---|
| Book | id, title, author, isbn, totalCopies, availableCopies |
| Member | id, name, email, phone |
| Loan | id, book, member, issueDate, dueDate, returnDate, fine |

## Business rules (in `LibraryService`)
1. A book can be issued only if `availableCopies > 0`. Borrowing decreases the count, returning increases it.
2. A member can hold at most **3** books at a time.
3. Due date = issue date + 14 days. Late returns are fined **Rs. 10 per day**.

## Project structure
```
library-management/
├── pom.xml
└── src
    ├── main/java/com/example/library
    │   ├── LibraryManagementApplication.java
    │   ├── DataLoader.java
    │   ├── controller/LibraryController.java
    │   ├── service/LibraryService.java
    │   ├── repository/BookRepository.java, MemberRepository.java, LoanRepository.java
    │   ├── model/Book.java, Member.java, Loan.java
    │   └── exception/LibraryException.java, GlobalExceptionHandler.java
    ├── main/resources/application.properties
    └── test/java/com/example/library/service/LibraryServiceTest.java
```

## REST endpoints
| Method | URL | Purpose |
|---|---|---|
| GET | `/api/hello` | Welcome message with Reg No |
| GET | `/api/books` | All books |
| GET | `/api/books/available` | Books with copies available |
| POST | `/api/books` | Add a book |
| GET | `/api/members` | All members |
| POST | `/api/members` | Add a member |
| GET | `/api/members/{id}/loans` | Loans of a member |
| POST | `/api/loans/borrow?memberId=&bookId=` | Borrow a book |
| PUT | `/api/loans/{loanId}/return?returnDate=` | Return a book (fine calculated) |

## How to run
```bash
cd library-management
mvn spring-boot:run        # app runs on http://localhost:8081
mvn test                   # runs the JUnit tests
```

## Output
```
$ curl -s http://localhost:8081/api/hello
Welcome to the Library Management System! Reg No: RA2311003011556

$ curl -s http://localhost:8081/api/books
[{"title":"Clean Code","author":"Robert C. Martin","isbn":"9780132350884","totalCopies":2,"availableCopies":2,"id":1},{"title":"Effective Java","author":"Joshua Bloch","isbn":"9780134685991","totalCopies":3,"availableCopies":3,"id":2},{"title":"Spring in Action","author":"Craig Walls","isbn":"9781617294945","totalCopies":1,"availableCopies":1,"id":3},{"title":"Head First Java","author":"Kathy Sierra","isbn":"9780596009205","totalCopies":4,"availableCopies":4,"id":4}]

$ curl -s -X POST 'http://localhost:8081/api/loans/borrow?memberId=1&bookId=3'
{"book":{"title":"Spring in Action","author":"Craig Walls","isbn":"9781617294945","totalCopies":1,"availableCopies":0,"id":3},"member":{"name":"Asha","email":"asha@example.com","phone":"9876543210","id":1},"issueDate":"2026-10-14","dueDate":"2026-10-28","fine":0.0,"id":1,"returnDate":null,"returned":false}

$ curl -s -X POST 'http://localhost:8081/api/loans/borrow?memberId=2&bookId=3'
{"error":"Book 'Spring in Action' is not available"}

$ curl -s -X POST 'http://localhost:8081/api/loans/borrow?memberId=1&bookId=1'
{"book":{"title":"Clean Code","author":"Robert C. Martin","isbn":"9780132350884","totalCopies":2,"availableCopies":1,"id":1},"member":{"name":"Asha","email":"asha@example.com","phone":"9876543210","id":1},"issueDate":"2026-10-14","dueDate":"2026-10-28","fine":0.0,"id":2,"returnDate":null,"returned":false}

$ curl -s -X POST 'http://localhost:8081/api/loans/borrow?memberId=1&bookId=2'
{"book":{"title":"Effective Java","author":"Joshua Bloch","isbn":"9780134685991","totalCopies":3,"availableCopies":2,"id":2},"member":{"name":"Asha","email":"asha@example.com","phone":"9876543210","id":1},"issueDate":"2026-10-14","dueDate":"2026-10-28","fine":0.0,"id":3,"returnDate":null,"returned":false}

$ curl -s -X POST 'http://localhost:8081/api/loans/borrow?memberId=1&bookId=4'
{"error":"Member 'Asha' has already borrowed the maximum of 3 books"}

$ curl -s -X PUT 'http://localhost:8081/api/loans/1/return?returnDate=2026-11-02'
{"book":{"title":"Spring in Action","author":"Craig Walls","isbn":"9781617294945","totalCopies":1,"availableCopies":1,"id":3},"member":{"name":"Asha","email":"asha@example.com","phone":"9876543210","id":1},"issueDate":"2026-10-14","dueDate":"2026-10-28","fine":50.0,"id":1,"returnDate":"2026-11-02","returned":true}

$ curl -s http://localhost:8081/api/books/available
[{"title":"Clean Code","author":"Robert C. Martin","isbn":"9780132350884","totalCopies":2,"availableCopies":1,"id":1},{"title":"Effective Java","author":"Joshua Bloch","isbn":"9780134685991","totalCopies":3,"availableCopies":2,"id":2},{"title":"Spring in Action","author":"Craig Walls","isbn":"9781617294945","totalCopies":1,"availableCopies":1,"id":3},{"title":"Head First Java","author":"Kathy Sierra","isbn":"9780596009205","totalCopies":4,"availableCopies":4,"id":4}]

```

### Automated tests (`mvn test`)
```
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.library.service.LibraryServiceTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Test cases
| # | Test case | Input | Expected | Actual | Result |
|---|---|---|---|---|---|
| 1 | Borrow available book | borrow memberId=1, bookId=3 | Loan created, due in 14 days, copies 1 → 0 | Loan id 1, due 2026-10-28, copies 0 | PASS |
| 2 | Borrow when no copies left | borrow memberId=2, bookId=3 | Error "not available" | `{"error":"Book 'Spring in Action' is not available"}` | PASS |
| 3 | Borrow 4th book | member 1 already has 3 books | Error "maximum of 3 books" | `{"error":"Member 'Asha' has already borrowed the maximum of 3 books"}` | PASS |
| 4 | Return 5 days late | return loan 1 on 2026-11-02 (due 2026-10-28) | fine = 50.0 | `"fine":50.0` | PASS |

## Spring Boot auto-configuration used
- `@SpringBootApplication` enables auto-configuration and component scanning.
- `spring-boot-starter-web` auto-configures embedded Tomcat, DispatcherServlet and Jackson JSON.
- `spring-boot-starter-data-jpa` + H2 auto-configure the DataSource, Hibernate, transactions and repository implementations.
- `application.properties` only overrides a few defaults; no beans are configured manually.
