package com.example.projet_restaurant_digitalcity.pl.models.dto;

import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProductItemDTO {

    private long id;
    private double quantity;
    private LocalDate expireDate;
    private String productTemplateName;

}

