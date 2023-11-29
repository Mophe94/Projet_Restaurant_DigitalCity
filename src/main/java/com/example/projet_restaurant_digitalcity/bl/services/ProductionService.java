package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ProductionService {
    ProductionTemplate getOneById(long id);

    Page<ProductionTemplate> getAll(int page,int countByPage);

    ProductionTemplate create(ProductionTemplate toCreate);

    ProductionTemplate update(long idProductionToUpdate, ProductionTemplate toUpdate);

    void delete(long id);

    List<ProductionItem> startProduction(long idProductionTemplate, int nbOfProduction, int quantityToStart);

    ProductionItem pauseProduction(long idProductionItem);

    void errorDuringProduction(long idProductionItem, ProductTemplate productUsed,double quantity, long idStorage );

    void finishProduction(long idProductionItem, long idStorageToStoreResult, LocalDate expireDateItemResult);
}
