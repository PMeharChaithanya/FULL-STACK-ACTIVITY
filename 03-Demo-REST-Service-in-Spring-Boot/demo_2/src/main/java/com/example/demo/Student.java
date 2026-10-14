package com.example.demo;

public class Student{
    private Integer rollno;
    private String name;
    private Float cgpa;

    public Student(){

    }

    public Student(Integer rollno,String name,Float cgpa){
        this.rollno=rollno;
        this.name=name;
        this.cgpa=cgpa;
    }

    public Student(Student s){
        this.rollno = s.rollno;
        this.name = s.name;
        this.cgpa = s.cgpa;
    }

    public Integer getRollno() {
        return rollno;
    }

    public void setRollno(Integer rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }
    

}