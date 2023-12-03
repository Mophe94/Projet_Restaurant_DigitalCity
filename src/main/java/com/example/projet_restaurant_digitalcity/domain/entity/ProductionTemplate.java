package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Production_Id",nullable = false)
    private long id;
    @Column(name = "Production_Name",nullable = false)
    private String name;
    @Column(name = "Production_Description",nullable = false)
    private String description;
    @Column(name = "Production_Method",nullable = false)
    private String method;
    @Column(name = "Production_Time_To_Make",nullable = false)
    private LocalTime timeToMake;
    @Column(name = "Production_Result_Quantity",nullable = false)
    private double resultQuantity;
    @Column(name = "Production_Measuring_Unit",nullable = false)
    private String measuringUnit;
    @Column(name = "Product_Result_Price")
    private double priceItemResult;
    @OneToOne
    @JoinColumn (name = "Product_result",nullable = true)
    private ProductTemplate productTemplateResult;
    @OneToMany(mappedBy = "idproductionTemplate", cascade = {CascadeType.PERSIST})
    private List<ProductUsage> productUsed ;

    @OneToMany(mappedBy = "productionTemplate")
    private Collection<ProductionItem> productionItem;


}
