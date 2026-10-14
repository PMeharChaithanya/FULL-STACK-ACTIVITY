package com.example.testingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testingdemo.model.Product;
import com.example.testingdemo.service.ProductService;

@SpringBootTest
class ProductIntegrationTest {

    @Autowired
    ProductService service;

    @Test
    void shouldSaveAndRetrieveProduct() {
        Product saved = service.saveProduct(new Product("Keyboard", 1500));
        Product found = service.getProduct(saved.getId());
        assertEquals("Keyboard", found.getName());
        assertEquals(1500, found.getPrice());
    }
}
