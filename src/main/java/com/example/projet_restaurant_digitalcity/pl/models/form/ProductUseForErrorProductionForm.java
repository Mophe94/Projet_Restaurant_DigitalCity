package com.example.projet_restaurant_digitalcity.pl.models.form;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class ProductUseForErrorProductionForm {

    @NotBlank
    String  nameProductTemplate;
    @Positive
    double quantity;
    @NotBlank
    String nameStorage;
    @Future
    LocalDate expireDate;
}
