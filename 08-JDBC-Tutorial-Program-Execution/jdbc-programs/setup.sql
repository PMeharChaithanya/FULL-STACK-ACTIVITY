-- Program 1: Student Management System
CREATE DATABASE IF NOT EXISTS college;
USE college;

CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(30),
    mark INT
);

-- Program 2: Supermarket Product Management
CREATE DATABASE IF NOT EXISTS supermarket;
USE supermarket;

CREATE TABLE IF NOT EXISTS product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(50),
    quantity INT,
    price DECIMAL(10, 2)
);
