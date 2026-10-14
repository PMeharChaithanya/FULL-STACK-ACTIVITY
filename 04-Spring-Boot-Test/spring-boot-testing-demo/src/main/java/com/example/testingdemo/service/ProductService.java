package com.example.testingdemo.service;
import com.example.testingdemo.model.Product;
import com.example.testingdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
 private final ProductRepository repository;
 public ProductService(ProductRepository repository){this.repository=repository;}
 public Product getProduct(Long id){return repository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));}
 public List<Product> getAllProducts(){return repository.findAll();}
 public Product saveProduct(Product product){return repository.save(product);}
}
