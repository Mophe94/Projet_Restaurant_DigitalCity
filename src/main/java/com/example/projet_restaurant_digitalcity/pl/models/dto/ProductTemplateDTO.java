package com.example.projet_restaurant_digitalcity.pl.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ProductTemplateDTO {

    private long id;
    private String name;
    private double price;
    private String origin;
    private String unitOfMeasurement;
    private String supplierName;

}
