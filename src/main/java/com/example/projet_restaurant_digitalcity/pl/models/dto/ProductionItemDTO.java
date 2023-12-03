package com.example.projet_restaurant_digitalcity.pl.models.dto;

import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductionItemDTO {

    private long id;
    private ProductionStatus status;
    private double ratio;
    private String NameOfProduction;
    private String nameOfWorker;
}
