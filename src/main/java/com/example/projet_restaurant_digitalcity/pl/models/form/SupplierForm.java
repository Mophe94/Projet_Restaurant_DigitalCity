package com.example.projet_restaurant_digitalcity.pl.models.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SupplierForm {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    private LocalTime openingHour;
    private LocalTime closeHour;
    @NotBlank
    private String email;

}
