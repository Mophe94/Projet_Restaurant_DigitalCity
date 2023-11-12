package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id",nullable = false)
    private long id;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name ="Order_ProductTemplate",
//            joinColumns = @JoinColumn(name = "Order_Id",nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "Product_Template_id",nullable = false)
//    )
//    private List<ProductTemplate>productTemplates;
//
//    @ManyToOne
//    @JoinColumn(name = "Linked_Worker",nullable = false)
//    private Worker worker;
//
//    @ManyToOne
//    @JoinColumn(name = "Linked_Supplier",nullable = false)
//    private Supplier supplier;
}
