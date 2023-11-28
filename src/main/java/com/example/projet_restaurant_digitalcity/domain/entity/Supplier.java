package com.example.projet_restaurant_digitalcity.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Supplier_id",nullable = false)
    private long id ;
    @Column(name = "Supplier_name",nullable = false)
    private String  name;
    @Column(name = "Supplier_Phone_Number",nullable = false)
    private String phoneNumber;
    @Column(name = "Supplier_Opening_Hour",nullable = false)
    private LocalTime openingHour;
    @Column(name = "Supplier_Close_Hour",nullable = false)
    private LocalTime closeHour;
    @Column(name = "Supplier_Email",nullable = false)
    private String email;
    @OneToMany(mappedBy = "supplier")
    private List<ProductTemplate>productTemplates;

    @OneToMany(mappedBy = "supplier")
    private List<OrderProduct>orders;


}
