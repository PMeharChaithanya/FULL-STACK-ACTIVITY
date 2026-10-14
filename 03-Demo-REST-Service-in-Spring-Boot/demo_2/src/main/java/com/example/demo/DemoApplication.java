package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Demo REST service started - Reg No: RA2311003011556");
	}

	@GetMapping("/")
	public String helloWorld(){
		return "Hello World from Spring Boot - Reg No: RA2311003011556";
	}

}
