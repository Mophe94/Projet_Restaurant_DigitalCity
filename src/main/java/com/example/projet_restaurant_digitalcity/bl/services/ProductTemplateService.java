package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductTemplateService {

    Page<ProductTemplate> getAll(int page , int countByPage);

    ProductTemplate getOneById(long id);

    ProductTemplate getOneByName(String name);

    ProductTemplate create(ProductTemplate toCreate);
    ProductTemplate createFromProductionTemplate(ProductionTemplate toCreate);
    ProductTemplate update(long id, ProductTemplate toCreate);

    void delete(long id);

    ProductTemplate setSupplierToProduct(long idSupplier, long idProduct);
}
