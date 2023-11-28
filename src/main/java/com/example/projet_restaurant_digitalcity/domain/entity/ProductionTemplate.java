package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
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
    private int resultQuantity;
    @Column(name = "Production_Measuring_Unit",nullable = false)
    private String measuringUnit;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Production_ProductTemplate",
            joinColumns = @JoinColumn(name = "Production_Id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "Product_Template_id",nullable = false)
    )
    private List<ProductTemplate> productTemplateList;



}
