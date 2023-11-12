package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;

import java.util.List;

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
    @Column(name = "Limit_Order_Product",nullable = false)
    private double limitOrder;
//    @OneToMany(mappedBy = "productTemplate")
//    private List<ProductItem>productItems;
//    @OneToOne(mappedBy = "productFromRecipe")
//    private Recipe productFromRecipe;
//    @ManyToOne
//    @JoinColumn(name = "Linked_supplier",nullable = false)
//    private Supplier supplier;

}
