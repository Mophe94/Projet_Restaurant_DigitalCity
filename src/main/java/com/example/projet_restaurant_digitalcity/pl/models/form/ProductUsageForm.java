package com.example.projet_restaurant_digitalcity.pl.models.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.LinkedHashMap;

@Data
@Builder
public class ProductUsageForm {

    @NotBlank
    private String nameProductTemplate;
    @Positive
    private double quantity;

}
