# Activity 6: Spring Boot First Application

**Reg No:** RA2311003011556

## Task
Run the given Spring Boot application on the local system and upload screenshots.

## Project
A simple college website built with Spring Boot MVC and Thymeleaf.
- `HomeController.java` – `@Controller` with two pages:
  - `GET /` → `index.html` (sends the message "Welcome to the Computer Science Department" through the `Model`)
  - `GET /about` → `about.html`
- `templates/index.html`, `templates/about.html` – Thymeleaf pages
- `static/css/style.css` – stylesheet

## What I did
- Ran the project with Maven.
- Added my Reg No to both pages.

## How to run
```bash
cd demo
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085
```

## Output
| URL | Page shows |
|---|---|
| `http://localhost:8085/` | "Welcome to the Computer Science Department", "We offer undergraduate and postgraduate programs.", Reg No: RA2311003011556 |
| `http://localhost:8085/about` | "About Our College", "Our college provides undergraduate and postgraduate education.", Reg No: RA2311003011556 |
