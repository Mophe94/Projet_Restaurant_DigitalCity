package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;

import java.util.List;

public interface ProductTemplateService {

    List<ProductTemplate> getAll();

    ProductTemplate getOneById(long id);

    ProductTemplate getOneByName(String name);

    ProductTemplate create(ProductTemplate toCreate);
    ProductTemplate update(long id, ProductTemplate toCreate);

    void delete(long id);




}
