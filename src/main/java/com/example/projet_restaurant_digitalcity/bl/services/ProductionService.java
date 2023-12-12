package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ProductionService {
    ProductionTemplate getOneById(long id);

    Page<ProductionTemplate> getAll(int page,int countByPage);

    ProductionTemplate create(ProductionTemplate toCreate);

    ProductionTemplate update(long idProductionToUpdate, ProductionTemplate toUpdate);

    void delete(long id);

    Page<ProductionItem> getAllWithStatutInProgress(int page, int countByPage);
    Page<ProductionItem> getAllWithStatutFinish(int page, int countByPage);
    Page<ProductionItem> getAllWithStatutFailed(int page, int countByPage);

   ProductionItem startProduction(long idProductionTemplate, int quantityToStart,String nameWorker);

    ProductionItem pauseProduction(long idProductionItem);

    ProductionItem unPauseProduction(long idProductionItem);

    void errorDuringProduction(long idProductionItem, List<ProductItem> productUsed);

    void finishProduction(long idProductionItem, long idStorageToStoreResult, LocalDate expireDateItemResult);

    void removeProductInStorage(double quantity,ProductTemplate productUsed);

}
