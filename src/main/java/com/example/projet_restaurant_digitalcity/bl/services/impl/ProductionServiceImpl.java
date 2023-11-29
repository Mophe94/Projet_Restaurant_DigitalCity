package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.ProductItemService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductTemplateRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionTemplateRepository;
import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import com.example.projet_restaurant_digitalcity.domain.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class ProductionServiceImpl implements ProductionService {

    private final ProductionTemplateRepository productionTemplateRepository;
    private final ProductionItemRepository productionItemRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductItemService productItemService;
    private final StorageService storageService;
    private final ProductTemplateRepository productTemplateRepository;

    public ProductionServiceImpl(ProductionTemplateRepository productionTemplateRepository, ProductionItemRepository productionItemRepository, ProductItemRepository productItemRepository, ProductItemService productItemService, StorageService storageService, ProductTemplateRepository productTemplateRepository) {
        this.productionTemplateRepository = productionTemplateRepository;
        this.productionItemRepository = productionItemRepository;
        this.productItemRepository = productItemRepository;
        this.productItemService = productItemService;

        this.storageService = storageService;
        this.productTemplateRepository = productTemplateRepository;
    }


    @Override
    public ProductionTemplate getOneById(long id) {
        return productionTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no production found with this id"));
    }

    @Override
    public Page<ProductionTemplate> getAll(int page,int countByPage) {
        return productionTemplateRepository.findAll(PageRequest.of(page, countByPage));
    }

    @Override
    public ProductionTemplate create(ProductionTemplate toCreate) {
        if (productionTemplateRepository.existsByName(toCreate.getName())) {
            throw new RuntimeException("this name is already use");
        }

        if (!productTemplateRepository.existsById(toCreate.getProductTemplate().getId())){
            ProductTemplate newProductTemplate = new ProductTemplate();
            newProductTemplate.setSupplier(null);
            newProductTemplate.setName(toCreate.getName());
            newProductTemplate.setPrice(0);
            newProductTemplate.setOrigin("Production");
            newProductTemplate.setId(0);
            productTemplateRepository.save(newProductTemplate);
        }
        return productionTemplateRepository.save(toCreate);
    }

    @Override
    public ProductionTemplate update(long idProductionToUpdate, ProductionTemplate toUpdate) {
        if (!productionTemplateRepository.existsById(idProductionToUpdate))
            throw new RuntimeException("no production found with this id");

        toUpdate.setId(idProductionToUpdate);
        return productionTemplateRepository.save(toUpdate);
    }

    @Override
    public void delete(long id) {
        productionTemplateRepository.deleteById(id);
    }




    @Override
    public List<ProductionItem> startProduction(long idProductionTemplate, int nbOfProduction, int ratio) {
        if (!productionTemplateRepository.existsById(idProductionTemplate))
            throw new RuntimeException("no production found with this id");

        List<ProductionItem> productionItems = null;
        for (int i = 0; i < nbOfProduction ; i++) {
            ProductionItem toCreate = new ProductionItem();
            toCreate.setId(0);
            toCreate.setStatus(ProductionStatus.JUST_STARTED);
            toCreate.setProductionTemplate(productionTemplateRepository.findById(idProductionTemplate)
                    .orElseThrow(() -> new RuntimeException("no production found with this id")));
            toCreate.setRatio(ratio);

            productionItems.add(toCreate);
            productionItemRepository.save(toCreate);

        }

        return productionItems;
    }


    @Override
    public ProductionItem pauseProduction(long idProductionItem) {
        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new RuntimeException("no production found with this id"));
        productionItem.setStatus(ProductionStatus.ON_PAUSE);
        productionItemRepository.save(productionItem);
        return productionItem;
    }



    @Override
    public void errorDuringProduction(long idProductionItem, ProductTemplate productUsed,double quantity, long idStorage ) {
        if (!productionItemRepository.existsById(idProductionItem))
            throw new RuntimeException("this production don't exist");

        Storage storage = storageService.getOneById(idStorage);

        double totalToRemove = 0;
        for (double i = quantity; i > 0; i = i - totalToRemove) {

            Optional<ProductItem> oldestProductItem = storage.getProductItems().stream()
                    .filter(productItem -> productItem.getProductTemplate().getName().equals(productUsed.getName()))
                    .min(Comparator.comparing(ProductItem::getExpireDate));


            double quantityInStock = oldestProductItem.orElseThrow(() -> new RuntimeException("fatal error")).getQuantity();

            if (quantityInStock < quantity) {
                quantity -= quantityInStock;

                totalToRemove += quantityInStock;
                productItemRepository.deleteById(oldestProductItem.orElseThrow().getId());
            }else {
                oldestProductItem.orElseThrow().setQuantity(quantityInStock - quantity);
                totalToRemove += quantity;

                productItemService.update(oldestProductItem.orElseThrow().getId(),oldestProductItem.orElseThrow());
            }
        }
        ProductionItem production = productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new RuntimeException("no production found with this id"));
        production.setStatus(ProductionStatus.FAILED);
        productionItemRepository.save(production);
    }

    @Override
    public void finishProduction(long idProductionItem, long idStorageToStoreResult, LocalDate expireDateItemResult) {
        if (!productionItemRepository.existsById(idProductionItem))
            throw new RuntimeException("this production don't exist");

        ProductionTemplate productionTemplate = productionTemplateRepository.findById(idProductionItem)
                .orElseThrow(() -> new RuntimeException("no ressource found with this id"));

        List<ProductTemplate> productToDelete = productionTemplate.getProductUsed().stream()
                .map(ProductUsage::getIdproductTemplate)
                .toList();

        for (ProductTemplate product : productToDelete) {
            double quantityToDelete =  productionItemRepository.findById(idProductionItem).orElseThrow().getProductionTemplate().getProductUsed().stream()
                    .filter(productUsage -> productUsage.getIdproductTemplate().getId() == product.getId())
                    .findFirst()
                    .map(ProductUsage::getQuantity).orElseThrow();

            double quantityWithRatioToDelete = quantityToDelete * productionItemRepository.findById(idProductionItem).orElseThrow().getRatio();

            double totalToRemove = 0;
            for (double i = quantityWithRatioToDelete; i > 0  ;i =  i - totalToRemove) {

                Optional<ProductItem> oldestProductItem = product.getProductItems().stream().min(Comparator.comparing(ProductItem::getExpireDate));

                double quantityInOneItem = oldestProductItem.orElseThrow().getQuantity();

                if (quantityInOneItem < quantityWithRatioToDelete){

                    quantityWithRatioToDelete -= quantityInOneItem;
                    totalToRemove += quantityInOneItem;
                    productItemRepository.deleteById(oldestProductItem.orElseThrow().getId());
                }else {
                    oldestProductItem.orElseThrow().setQuantity(quantityInOneItem - quantityWithRatioToDelete);
                    totalToRemove += quantityWithRatioToDelete;

                    productItemService.update(oldestProductItem.orElseThrow().getId(),oldestProductItem.orElseThrow());
                }
            }
            ProductionItem productionItem =  productionItemRepository.findById(idProductionItem).orElseThrow();
            productionItem.setStatus(ProductionStatus.FINISH);
            productionItemRepository.save(productionItem);

            ProductItem productItemResult = new ProductItem();
            productItemResult.setId(0);
            productItemResult.setProductTemplate(productionItem.getProductionTemplate().getProductTemplate());
            productItemResult.setStorage(storageService.getOneById(idStorageToStoreResult));
            productItemResult.setExpireDate(expireDateItemResult);
            productItemResult.setQuantity(productionItem.getProductionTemplate().getResultQuantity());

            productItemRepository.save(productItemResult);



        }
    }
}
