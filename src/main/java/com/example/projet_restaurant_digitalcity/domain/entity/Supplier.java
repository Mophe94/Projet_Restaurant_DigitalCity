package com.example.projet_restaurant_digitalcity.entity;


import jakarta.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Supplier_id",nullable = false)
    private long id ;
    @Column(name = "Supplier_Phone_Number",nullable = false)
    private String phoneNumber;
    @Column(name = "Supplier_Opening_Hour",nullable = false)
    private String OpeningHour;
    @Column(name = "Supplier_Email",nullable = false)
    private String Email;

}
