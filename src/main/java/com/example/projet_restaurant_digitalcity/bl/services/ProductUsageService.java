package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductUsageService {

    List<ProductUsage> addProductUsageListForProductionTemplate(List<ProductUsage> productUsages,long idProduction);


    void deleteProductUsageInProduction(String nameProduct,long idProduction);



}
