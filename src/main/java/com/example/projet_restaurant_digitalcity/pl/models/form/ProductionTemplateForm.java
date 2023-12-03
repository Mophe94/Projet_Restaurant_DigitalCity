package com.example.projet_restaurant_digitalcity.pl.models.form;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ProductionTemplateForm {

    private String name;
    private String description;
    private String method;
    private LocalTime timeToMake;
    private Double resultQuantity;
    private String measuringUnit;
    private double priceItemResult;
}
