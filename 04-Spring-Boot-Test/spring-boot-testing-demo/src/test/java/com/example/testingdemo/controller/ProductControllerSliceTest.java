package com.example.testingdemo.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testingdemo.model.Product;
import com.example.testingdemo.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerSliceTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    ProductService service;

    @Test
    void shouldReturnProduct() throws Exception {
        when(service.getProduct(1L)).thenReturn(new Product(1L, "Laptop", 65000));
        mockMvc.perform(get("/products/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Laptop")).andExpect(jsonPath("$.price").value(65000));
    }
}
