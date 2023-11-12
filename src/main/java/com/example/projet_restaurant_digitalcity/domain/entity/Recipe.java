package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Recipe_Id",nullable = false)
    private long id;
    @Column(name = "Recipe_Name",nullable = false)
    private String name;
    @Column(name = "Recipe_Description",nullable = false)
    private String description;
    @Column(name = "Recipe_Method",nullable = false)
    private String method;
    @Column(name = "Recipe_Time_To_Make",nullable = false)
    private LocalDateTime timeToMake;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "Recipe_ProductTemplate",
//            joinColumns = @JoinColumn(name = "Recipe_Id",nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "Product_Template_id",nullable = false)
//    )
//    private List<ProductTemplate> productTemplateList;

//    @OneToOne
//    @JoinColumn(name = "Product_From_Recipe",nullable = false)
//    private ProductTemplate productFromRecipe;
}
