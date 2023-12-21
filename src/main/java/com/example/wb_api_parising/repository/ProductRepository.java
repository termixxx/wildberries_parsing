package com.example.wb_api_parising.repository;

import com.example.wb_api_parising.entiti.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
