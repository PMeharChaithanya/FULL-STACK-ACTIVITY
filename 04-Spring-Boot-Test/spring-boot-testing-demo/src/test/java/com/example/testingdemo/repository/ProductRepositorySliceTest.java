package com.example.testingdemo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.testingdemo.model.Product;

@DataJpaTest
class ProductRepositorySliceTest {

    @Autowired
    ProductRepository repository;

    @Test
    void shouldSaveAndFindProduct() {
        Product saved = repository.save(new Product("Mouse", 800));
        Product found = repository.findById(saved.getId()).orElseThrow();
        assertEquals("Mouse", found.getName());
        assertEquals(800, found.getPrice());
    }
}
