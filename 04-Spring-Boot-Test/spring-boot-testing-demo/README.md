# Spring Boot Unit, Slice and Integration Testing Demo

Architecture: Controller -> Service -> Repository -> H2 Database

## Unit testing
`ProductServiceUnitTest.java` uses JUnit + Mockito. The repository is mocked and the service is tested in isolation.

`mvn -Dtest=ProductServiceUnitTest test`

## Slice testing
`ProductControllerSliceTest.java` uses `@WebMvcTest` to test only the MVC/controller slice.

`ProductRepositorySliceTest.java` uses `@DataJpaTest` to test the JPA repository with H2.

## Integration testing
`ProductIntegrationTest.java` uses `@SpringBootTest` and verifies Spring components working together with H2.

## Run all tests
`mvn test`

## Run application
`mvn spring-boot:run`

Endpoints: GET /products, GET /products/1, POST /products
