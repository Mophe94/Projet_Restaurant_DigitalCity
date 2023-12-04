package com.example.projet_restaurant_digitalcity.pl.models.form;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ProductionTemplateForm {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String method;
    private LocalTime timeToMake;
    @Positive
    private Double resultQuantity;
    @NotBlank
    private String measuringUnit;
    @Positive
    private double priceItemResult;
}
