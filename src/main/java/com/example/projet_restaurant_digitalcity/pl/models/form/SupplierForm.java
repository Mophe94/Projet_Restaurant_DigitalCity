package com.example.projet_restaurant_digitalcity.pl.models.form;

import com.example.projet_restaurant_digitalcity.pl.models.dto.OrderProductDTO;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductTemplateDTO;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SupplierForm {

    private String name;
    private String phoneNumber;
    private LocalTime openingHour;
    private LocalTime closeHour;
    private String email;

}
