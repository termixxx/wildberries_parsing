package com.example.wb_api_parising.service.impl;

import com.example.wb_api_parising.entiti.Product;
import com.example.wb_api_parising.repository.ProductRepository;
import com.example.wb_api_parising.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
