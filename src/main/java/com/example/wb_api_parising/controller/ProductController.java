package com.example.wb_api_parising.controller;

import com.example.wb_api_parising.entiti.Product;
import com.example.wb_api_parising.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getInfo() {
        return productService.getAllProducts();
    }
}
