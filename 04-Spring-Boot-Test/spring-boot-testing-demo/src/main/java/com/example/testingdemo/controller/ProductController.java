package com.example.testingdemo.controller;
import com.example.testingdemo.model.Product;
import com.example.testingdemo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/products")
public class ProductController {
 private final ProductService service;
 public ProductController(ProductService service){this.service=service;}
 @GetMapping public List<Product> getAllProducts(){return service.getAllProducts();}
 @GetMapping("/{id}") public Product getProduct(@PathVariable Long id){return service.getProduct(id);}
 @PostMapping public Product saveProduct(@RequestBody Product product){return service.saveProduct(product);}
}
