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
    private double priceKG;
    @Column(name = "Origin_Product",nullable = false)
    private String origin;
    @Column(name = "Limit_Order_Product",nullable = false)
    private double limitOrder;
    @OneToMany(mappedBy = "productTemplate")
    private List<ProductItem>productItems;
    @OneToOne
    @JoinColumn(name = "Linked_Recipe",nullable = true)
    private Recipe linkedRecipe;
    @ManyToOne
    @JoinColumn(name = "Linked_supplier",nullable = false)
    private Supplier supplier;

}
