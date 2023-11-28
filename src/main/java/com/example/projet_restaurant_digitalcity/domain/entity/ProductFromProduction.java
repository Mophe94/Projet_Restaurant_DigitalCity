package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFromProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductFromProduction_ID",nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "Linked_Storage",nullable = false)
    private Storage storage;
    @OneToOne()
    @JoinColumn(name = "Linked_Production_item",nullable = false)
    private ProductionItem productionItem;


}
