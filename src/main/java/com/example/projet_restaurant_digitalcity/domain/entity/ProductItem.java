package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_item_Id",nullable = false)
    private long id;
    @Column(name = "Product_Item_Quantity",nullable = false)
    private double quantity;

    @Column(name = "Product_Item_Expire_Date",nullable = false)
    private LocalDate expiryDate;
//    @ManyToOne
//    @JoinColumn(name = "ProductTemplate",nullable = false)
//    private ProductTemplate productTemplate;
//    @ManyToOne
//    @JoinColumn(name = "Linked_storage",nullable = false)
//    private Storage storage;
}
