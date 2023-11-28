package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Template_id", nullable = false,unique = true)
    private long id;
    @Column(name = "Name_Product",nullable = false,unique = true)
    private String name;
    @Column(name = "Price_Product",nullable = false)
    private double price;
    @Column(name = "Origin_Product",nullable = false)
    private String origin;
    @Column(name = "Limit_To_Order_Product",nullable = false)
    private double limitToOrder;
    @Column(name = "Measuring_Unit",nullable = false)
    private String measuringUnit;
    @Column(name = "Limit_When_Order",nullable = false)
    private int limitWhenOrder;
    @ManyToOne
    @JoinColumn(name = "Linked_supplier")
    private Supplier supplier;

}
