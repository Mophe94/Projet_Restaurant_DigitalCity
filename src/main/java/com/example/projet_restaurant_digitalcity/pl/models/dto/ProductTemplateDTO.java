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
    private double priceKG;
    private String origin;
    private double limitOrder;
    private SupplierDTO supplier;

    @Data
    @Builder
    public static class SupplierDTO {

        private long id;
        private String phoneNumber;
        private LocalTime openingHour;
        private LocalTime closeHour;
        private String email;

    }

}
