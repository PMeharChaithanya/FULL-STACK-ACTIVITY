# Activity 4: Spring Boot Test

**Reg No:** RA2311003011556

## Task
Run the Spring Boot testing project on the local machine and show the output.

## Types of tests in this project
| Test | Type | What it does |
|---|---|---|
| `ProductServiceUnitTest` | Unit test | JUnit + Mockito. The repository is mocked; the service is tested alone. |
| `ProductControllerSliceTest` | Slice test | `@WebMvcTest` loads only the web layer and tests the controller with MockMvc. |
| `ProductRepositorySliceTest` | Slice test | `@DataJpaTest` tests the JPA repository with H2. |
| `ProductIntegrationTest` | Integration test | `@SpringBootTest` loads the full application and tests all layers together. |

## What I did
- Ran all tests with `mvn test` – all passed.
- Ran the application and called the endpoints.
- Added a `HomeController` with `/` showing my Reg No.

## How to run
```bash
cd spring-boot-testing-demo
mvn test
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8084
```

## Output

### `mvn test`
```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.testingdemo.repository.ProductRepositorySliceTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.testingdemo.ProductIntegrationTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.testingdemo.controller.ProductControllerSliceTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in com.example.testingdemo.service.ProductServiceUnitTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Browser
| URL | Response |
|---|---|
| `http://localhost:8084/` | `Spring Boot Testing Demo - Reg No: RA2311003011556` |
| `http://localhost:8084/products` (after adding a product with POST) | `[{"id":1,"name":"Laptop","price":65000.0}]` |
