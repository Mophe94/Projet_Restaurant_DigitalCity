package com.example.projet_restaurant_digitalcity.pl.models.form;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductTemplateForm {


    @NotBlank
    @Size(min = 0,max = 20)
    private String name;
    @Positive
    private double price;
    @NotBlank
    private String origin;
    @Positive
    private double limitToOrder;
    @NotBlank
    private String unitOfMeasurement;
    private int limitWhenOrder;

}
