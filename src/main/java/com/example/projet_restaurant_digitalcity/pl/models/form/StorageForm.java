package com.example.projet_restaurant_digitalcity.pl.models.form;

import com.example.projet_restaurant_digitalcity.domain.StorageType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageForm {

    @NotNull
    private String name;
    @NotNull
    private StorageType storageType;



}
