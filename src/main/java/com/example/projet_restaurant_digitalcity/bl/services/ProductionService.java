package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ProductionService {
    ProductionTemplate getOneById(long id);

    Page<ProductionTemplate> getAll(int page,int countByPage);

    ProductionTemplate create(ProductionTemplate toCreate);

    ProductionTemplate update(long idProductionToUpdate, ProductionTemplate toUpdate);

    void delete(long id);

   ProductionItem startProduction(long idProductionTemplate, int quantityToStart,String nameWorker);

    ProductionItem pauseProduction(long idProductionItem);

    ProductionItem unPauseProduction(long idProductionItem);

    void errorDuringProduction(long idProductionItem, ProductTemplate productUsed,double quantity,String nameStorage );

    void finishProduction(long idProductionItem, long idStorageToStoreResult, LocalDate expireDateItemResult);

    void removeProductInStorage(double quantity, Storage storage,ProductTemplate productUsed);

}
