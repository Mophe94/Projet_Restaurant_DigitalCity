package com.example.projet_restaurant_digitalcity.domain.entity;

import com.example.projet_restaurant_digitalcity.domain.StorageType;
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
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Storage_Id",nullable = false,unique = true)
    private long id;
    @Column(name = "Storage_Name",nullable = false,unique = true)
    private String name;
    @Column(name = "Storage_Type",nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @OneToMany(mappedBy = "storage")
    private List<ProductItem> productItems;


}
