package com.example.projet_restaurant_digitalcity.pl.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderProductDTO {

    private long id;
    private List<ProductTemplateDTO> productTemplates;
    private WorkerDTO worker;
    private SupplierDTO supplier;
}
