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
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id",nullable = false)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="Order_ProductTemplate",
            joinColumns = @JoinColumn(name = "Order_Id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "Product_Template_id",nullable = false)
    )
    private List<ProductTemplate> productTemplates;

    @ManyToOne
    @JoinColumn(name = "Linked_Worker",nullable = false)
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "Linked_Supplier",nullable = false)
    private Supplier supplier;
}
