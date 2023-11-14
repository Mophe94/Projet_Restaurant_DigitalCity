package com.example.projet_restaurant_digitalcity.pl.models.form;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ProductItemForm {

    @Positive
    private  double quantity;
    @Future
    private LocalDate expireDate;
    @Positive
    private long productTemplateId;
    @Positive
    private long storageId;

}
