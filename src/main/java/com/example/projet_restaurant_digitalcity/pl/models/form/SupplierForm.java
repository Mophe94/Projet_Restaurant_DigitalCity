package com.example.projet_restaurant_digitalcity.pl.models.form;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SupplierForm {

    private String name;
    private String phoneNumber;
    private LocalTime openingHour;
    private LocalTime closeHour;
    private String email;

}
