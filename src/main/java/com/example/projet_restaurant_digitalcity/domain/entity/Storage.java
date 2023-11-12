package com.example.projet_restaurant_digitalcity.entity;

import jakarta.persistence.*;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Storage_Id",nullable = false)
    private long id;

    private
}
