package com.example.projet_restaurant_digitalcity.pl.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class RecipeDTO {

    private long id;
    private String name;
    private String description;
    private String method;
    private LocalDateTime timeToMake;
    private List<ProductTemplateDTO>productTemplates;
    private ProductTemplateDTO productFromRecipe;

}
