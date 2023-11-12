package com.example.projet_restaurant_digitalcity.pl.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductTemplateDTO {

    private long id;
    private String name;
    private double priceKG;
    private String origin;
    private double limitOrder;
    private List<ProductItemDTO> productItems;
    private RecipeDTO recipe;
    private SupplierDTO supplier;

}
