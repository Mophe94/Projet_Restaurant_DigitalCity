package com.example.projet_restaurant_digitalcity.pl.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUsageDTO {


    private double quantity;
    private String nameOfProductUsage;

}
