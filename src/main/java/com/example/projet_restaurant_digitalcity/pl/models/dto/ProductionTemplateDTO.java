package com.example.projet_restaurant_digitalcity.pl.models.dto;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductionTemplateDTO {

    private long id;
    private String name;
    private String description;
    private String method;
    private double resultQuantity;
    private String measuringUnit;
    private String productResult;
    private List<ProductUsageDTO> productUsedList;

}
