package com.example.wb_api_parising.service;

import com.example.wb_api_parising.entiti.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void save(Product product);
    List<Product> getAllProducts();
}
