# Activity 5: Test Driven Spring Boot App

**Reg No:** RA2311003011556

## Task
Run the given test-driven Spring Boot app on the local machine with Maven.

## TDD cycle demonstrated
1. Write a failing test.
2. Write the minimum production code to pass it.
3. Make the test pass.
4. Refactor.

## Tests
| Test | What it checks |
|---|---|
| `ProductServiceTest` (2 tests) | Service returns a product by id, and throws an error when the product is not found (repository mocked with Mockito) |
| `ProductControllerTest` (1 test) | `GET /products/1` returns status 200 with the correct JSON (`@WebMvcTest` + MockMvc) |

## What I did
- Ran `mvn test` – all 3 tests passed.
- Added a `DataLoader` that inserts one sample product at startup so `GET /products/1` works in the browser.
- Added a `HomeController` with `/` showing my Reg No.

## How to run
```bash
cd spring-tdd-demo
mvn test
mvn spring-boot:run          # runs on http://localhost:8080
```

## Output

### `mvn test`
```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.demo.controller.ProductControllerTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.demo.service.ProductServiceTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Browser
| URL | Response |
|---|---|
| `http://localhost:8080/` | `Spring Boot TDD Demo - Reg No: RA2311003011556` |
| `http://localhost:8080/products/1` | `{"id":1,"name":"Laptop","price":65000.0}` |
