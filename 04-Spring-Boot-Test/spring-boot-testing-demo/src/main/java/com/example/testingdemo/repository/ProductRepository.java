package com.example.testingdemo.repository;
import com.example.testingdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long>{}
