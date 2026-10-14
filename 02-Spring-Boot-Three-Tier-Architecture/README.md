# Activity 2: Spring Boot Three Tier Architecture

**Reg No:** RA2311003011556

## Task
Run the given Spring Boot three-tier demo project on the local machine and share the output in the browser.

## Architecture
```
Browser / REST Client
        ▼
ProductController  (@RestController)  – Presentation tier
        ▼
ProductService     (@Service)         – Business tier
        ▼
ProductRepository  (JpaRepository)    – Data access tier
        ▼
H2 Database
```

## What I did
- Ran the project with Maven (`mvn spring-boot:run`).
- Added a `HomeController` with a `/` endpoint that shows my Reg No, and a startup message with the Reg No.
- Tested GET all, GET by id, and POST.

## How to run
```bash
cd spring-boot-three-tier-demo
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8082
```

## Output
| URL | Response |
|---|---|
| `http://localhost:8082/` | `Spring Boot Three-Tier Demo - Reg No: RA2311003011556` |
| `http://localhost:8082/api/products` | `[{"id":1,"name":"Laptop","price":65000.0},{"id":2,"name":"Mouse","price":800.0},{"id":3,"name":"Keyboard","price":1500.0}]` |
| `http://localhost:8082/api/products/1` | `{"id":1,"name":"Laptop","price":65000.0}` |
| `POST /api/products` with `{"name":"Monitor","price":12000}` | `{"id":4,"name":"Monitor","price":12000.0}` |

## Learning points
- `@RestController` = presentation tier, `@Service` = business tier, `JpaRepository` = data access tier
- Constructor injection = dependency injection
- Spring Boot manages the objects through the Spring container
