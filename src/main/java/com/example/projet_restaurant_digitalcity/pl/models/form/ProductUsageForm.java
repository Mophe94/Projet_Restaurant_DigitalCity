package com.example.projet_restaurant_digitalcity.pl.models.form;

import lombok.*;

import java.util.LinkedHashMap;

@Data
@Builder
public class ProductUsageForm {

    private String nameProductTemplate;
    private double quantity;

}
