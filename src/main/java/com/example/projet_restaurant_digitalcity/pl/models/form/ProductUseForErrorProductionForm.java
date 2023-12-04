package com.example.projet_restaurant_digitalcity.pl.models.form;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class ProductUseForErrorProductionForm {

    String  nameProductTemplate;
    double quantity;
    String nameStorage;
    LocalDate expireDate;
}
