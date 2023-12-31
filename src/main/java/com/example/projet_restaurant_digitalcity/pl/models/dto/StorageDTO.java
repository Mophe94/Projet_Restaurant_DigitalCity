package com.example.projet_restaurant_digitalcity.pl.models.dto;

import com.example.projet_restaurant_digitalcity.domain.StorageType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class StorageDTO {

    private long id;
    private String name;
    private StorageType storageType;
}
