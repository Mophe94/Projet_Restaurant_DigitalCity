package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductionService {
    ProductionTemplate getOneById(long id);

    Page<ProductionTemplate> getAll(int page,int countByPage);

    ProductionTemplate create(ProductionTemplate toCreate);

    ProductionTemplate update(long idProductionToUpdate, ProductionTemplate toUpdate);

    void delete(long id);

    Page<ProductionItem> startProduction(long idProductionTemplate, int quantityToStart,int page ,int countByPage);

    ProductionItem pauseProduction( long idProductionItem);

    List<ProductTemplate> errorDuringProduction(long idProductionItem, List<ProductTemplate> productAlreadyUsed);

    ProductionItem finishProduction(long idProductionItem);
}
