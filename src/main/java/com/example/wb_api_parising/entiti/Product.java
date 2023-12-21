package com.example.wb_api_parising.entiti;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    private Long id;
    private String brand;
    private String name;
    private BigDecimal salePriceU;
    private Double reviewRating;
    private int feedbacks;

    public Product(Long id, String brand, String name, BigDecimal salePriceU, Double reviewRating, int feedbacks) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.salePriceU = salePriceU;
        this.reviewRating = reviewRating;
        this.feedbacks = feedbacks;
    }

    public Product() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalePriceU() {
        return salePriceU;
    }

    public void setSalePriceU(BigDecimal salePriceU) {
        this.salePriceU = salePriceU;
    }

    public Double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(int feedbacks) {
        this.feedbacks = feedbacks;
    }
}
