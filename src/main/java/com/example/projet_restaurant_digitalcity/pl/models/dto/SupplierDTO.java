package com.example.projet_restaurant_digitalcity.pl.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class SupplierDTO {

    private long id;
    private String phoneNumber;
    private LocalTime openingHour;
    private LocalTime closeHour;
    private String email;
    private List<ProductTemplateDTO>productTemplates;
    private List<OrderProductDTO>orders;
}
