package com.example.wb_api_parising.entiti.temp;

import com.example.wb_api_parising.entiti.Product;

import java.util.List;

public class Data {
    private List<Product> products;

    public Data(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
