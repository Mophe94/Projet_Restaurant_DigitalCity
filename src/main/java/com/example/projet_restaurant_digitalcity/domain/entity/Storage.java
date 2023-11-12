package com.example.projet_restaurant_digitalcity.domain.entity;

import com.example.projet_restaurant_digitalcity.domain.StorageType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Storage_Id",nullable = false)
    private long id;
    @Column(name = "Storage_Type",nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;
    @Column(name = "Temperature",nullable = false)
    private double temperature;

//    @OneToMany(mappedBy = "storage")
//    private List<ProductItem> productItems;
}
