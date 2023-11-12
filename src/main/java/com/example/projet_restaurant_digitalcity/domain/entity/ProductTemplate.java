package com.example.projet_restaurant_digitalcity.entity;

import jakarta.persistence.*;

@Entity

public class ProductTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Template_id", nullable = false)
    private long id;
    @Column(name = "Name_Product",nullable = false)
    private String name;
    @Column(name = "Price_Product",nullable = false)
    private double priceKG;
    @Column(name = "Origin_Product",nullable = false)
    private String origin;
    @Column(name = "Limit_Order_Product")
    private int limitOrder;
}
