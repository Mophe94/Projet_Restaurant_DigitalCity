package com.example.projet_restaurant_digitalcity.domain.entity;

import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Production_Item_ID",nullable = false)
    private long id;
    @Column(name = "Production_Item_Status",nullable = true)
    @Enumerated(EnumType.STRING)
    private ProductionStatus status;
    @ManyToOne
    @JoinColumn(name = "Linked_Production_Template")
    private ProductionTemplate productionTemplate;
    @OneToOne(mappedBy = "productionItem")
    private ProductFromProduction productFromProduction;



}
