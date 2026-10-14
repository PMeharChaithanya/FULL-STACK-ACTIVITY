package com.example.testingdemo.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.testingdemo.model.Product;
import com.example.testingdemo.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceUnitTest {

    @Mock
    ProductRepository repository;
    @InjectMocks
    ProductService service;

    @Test
    void shouldReturnProduct() {
        Product p = new Product(1L, "Laptop", 65000);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        Product r = service.getProduct(1L);
        assertEquals("Laptop", r.getName());
        assertEquals(65000, r.getPrice());
        verify(repository).findById(1L);
    }
}
