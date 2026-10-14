# Spring Boot Three-Tier Architecture Demo

## Architecture

Browser / REST Client
        |
        v
ProductController (@RestController)
        |
        v
ProductService (@Service)
        |
        v
ProductRepository (Spring Data JPA)
        |
        v
H2 Database

## Requirements

- Java 17+
- Maven 3.9+ recommended

## Run

mvn spring-boot:run

## REST endpoints

GET all products:
http://localhost:8080/api/products

GET one product:
http://localhost:8080/api/products/1

POST:
http://localhost:8080/api/products

JSON:
{
  "name": "Monitor",
  "price": 12000
}

## H2 Console

http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:shopdb

Username:
sa

Password:
leave blank

## Learning points

- @RestController = Presentation tier
- @Service = Business tier
- JpaRepository = Data access tier
- Constructor injection = Dependency Injection
- Spring Boot manages the application objects through the Spring container
