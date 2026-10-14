package com.example.threetier.config;

import com.example.threetier.entity.Product;
import com.example.threetier.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductRepository repository) {
        return args -> {
            repository.save(new Product("Laptop", 65000));
            repository.save(new Product("Mouse", 800));
            repository.save(new Product("Keyboard", 1500));
        };
    }
}
