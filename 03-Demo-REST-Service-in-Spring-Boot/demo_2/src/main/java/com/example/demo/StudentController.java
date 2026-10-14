package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController{
    @GetMapping
    public List<Student> getStudents(){
        return List.of(
            new Student(1,"Raja",9.8F),
            new Student(2,"Ramu",8.7F)
        );
    }
}