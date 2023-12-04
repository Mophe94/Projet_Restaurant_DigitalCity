package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.*;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionTemplateRepository;
import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import com.example.projet_restaurant_digitalcity.domain.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionTemplateRepository productionTemplateRepository;
    private final ProductionItemRepository productionItemRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductItemService productItemService;
    private final StorageService storageService;
    private final ProductTemplateService productTemplateService;
    private final WorkerService workerService;

    public ProductionServiceImpl(ProductionTemplateRepository productionTemplateRepository, ProductionItemRepository productionItemRepository, ProductItemRepository productItemRepository, ProductItemService productItemService, StorageService storageService, ProductTemplateService productTemplateService, WorkerService workerService) {
        this.productionTemplateRepository = productionTemplateRepository;
        this.productionItemRepository = productionItemRepository;
        this.productItemRepository = productItemRepository;
        this.productItemService = productItemService;

        this.storageService = storageService;
        this.productTemplateService = productTemplateService;
        this.workerService = workerService;
    }


    @Override
    public ProductionTemplate getOneById(long id) {
        return productionTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no production found with this id"));
    }

    @Override
    public Page<ProductionTemplate> getAll(int page, int countByPage) {
        return productionTemplateRepository.findAll(PageRequest.of(page, countByPage));
    }

    @Override
    public ProductionTemplate create(ProductionTemplate toCreate) {
        if (productionTemplateRepository.existsByName(toCreate.getName())) {
            throw new RuntimeException("this name is already use");
        }
        toCreate.setProductTemplateResult(productTemplateService.createFromProductionTemplate(toCreate));
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
    public ProductionItem startProduction(long idProductionTemplate, int ratio, String nameWorker) {
        if (!productionTemplateRepository.existsById(idProductionTemplate))
            throw new RuntimeException("no production found with this id");

        List<ProductUsage> productUse = productionTemplateRepository.findById(idProductionTemplate).orElseThrow()
                .getProductUsed();


        for (ProductUsage product : productUse) {
            if (!checkProductInStock(product.getIdproductTemplate(),product.getQuantity()))
                throw new RuntimeException("not enough of " + product.getIdproductTemplate().getName() + " in stock ");

            removeProductInStorage(product.getQuantity()*ratio,product.getIdproductTemplate());
        }

        ProductionItem toCreate = new ProductionItem();
        toCreate.setId(0);
        toCreate.setStatus(ProductionStatus.IN_PROGRESS);
        toCreate.setProductionTemplate(productionTemplateRepository.findById(idProductionTemplate)
                .orElseThrow(() -> new RuntimeException("no production found with this id")));
        toCreate.setRatio(ratio);
        toCreate.setWorker(workerService.findByName(nameWorker));

        productionItemRepository.save(toCreate);


        return toCreate;
    }


    @Override
    public ProductionItem pauseProduction(long idProductionItem) {
        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(() -> new RuntimeException("no production found with this id"));
        productionItem.setStatus(ProductionStatus.ON_PAUSE);
        productionItemRepository.save(productionItem);
        return productionItem;
    }

    @Override
    public ProductionItem unPauseProduction(long idProductionItem) {
        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(() -> new RuntimeException("no production found with this id"));
        productionItem.setStatus(ProductionStatus.IN_PROGRESS);
        productionItemRepository.save(productionItem);
        return productionItem;

    }

    @Override
    public void errorDuringProduction(long idProductionItem, List<ProductItem> productUsed) {
        if (!productionItemRepository.existsById(idProductionItem))
            throw new RuntimeException("this production don't exist");

        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow();
        if (productionItem.getStatus() == ProductionStatus.FAILED)
            throw new RuntimeException("this production is already failed");

        for (ProductItem product: productUsed) {

        productItemService.addProductInStorage(
                product.getStorage().getId(),
                product);
        }

        ProductionItem production = productionItemRepository.findById(idProductionItem)
                .orElseThrow(() -> new RuntimeException("no production found with this id"));
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
            double quantityToDelete = productionItemRepository.findById(idProductionItem).orElseThrow().getProductionTemplate().getProductUsed().stream()
                    .filter(productUsage -> productUsage.getIdproductTemplate().getId() == product.getId())
                    .findFirst()
                    .map(ProductUsage::getQuantity).orElseThrow();

            double quantityWithRatioToDelete = quantityToDelete * productionItemRepository.findById(idProductionItem).orElseThrow().getRatio();

            double totalToRemove = 0;
            for (double i = quantityWithRatioToDelete; i > 0; i = i - totalToRemove) {

                Optional<ProductItem> oldestProductItem = product.getProductItems().stream().min(Comparator.comparing(ProductItem::getExpireDate));

                double quantityInOneItem = oldestProductItem.orElseThrow().getQuantity();

                if (quantityInOneItem < quantityWithRatioToDelete) {

                    quantityWithRatioToDelete -= quantityInOneItem;
                    totalToRemove += quantityInOneItem;
                    productItemRepository.deleteById(oldestProductItem.orElseThrow().getId());
                } else {
                    oldestProductItem.orElseThrow().setQuantity(quantityInOneItem - quantityWithRatioToDelete);
                    totalToRemove += quantityWithRatioToDelete;

                    productItemService.update(oldestProductItem.orElseThrow().getId(), oldestProductItem.orElseThrow());
                }
            }
            ProductionItem productionItem = productionItemRepository.findById(idProductionItem).orElseThrow();
            productionItem.setStatus(ProductionStatus.FINISH);
            productionItemRepository.save(productionItem);

            ProductItem productItemResult = new ProductItem();
            productItemResult.setId(0);
            productItemResult.setProductTemplate(productionItem.getProductionTemplate().getProductTemplateResult());
            productItemResult.setStorage(storageService.getOneById(idStorageToStoreResult));
            productItemResult.setExpireDate(expireDateItemResult);
            productItemResult.setQuantity(productionItem.getProductionTemplate().getResultQuantity());

            productItemRepository.save(productItemResult);


        }

    }

    @Override
    public void removeProductInStorage(double quantity, ProductTemplate productUsed) {

        while (quantity > 0) {

            ProductItem oldestProductItem = productItemRepository.findOldestWithName(productUsed.getName())
                    .orElseThrow(() -> new RuntimeException("fatal error"));

            if (oldestProductItem.getQuantity() < quantity) {
                quantity -= oldestProductItem.getQuantity();

                productItemRepository.deleteById(oldestProductItem.getId());
            } else {
                oldestProductItem.setQuantity(oldestProductItem.getQuantity() - quantity);

                productItemService.update(oldestProductItem.getId(), oldestProductItem);
                quantity = 0;
            }
        }
    }

    public Boolean checkProductInStock(ProductTemplate productToCheck, double quantity) {

        List<ProductItem> productInStock = productItemRepository.findAllByProductTemplate(productToCheck);

        return (productInStock.stream().mapToDouble(ProductItem::getQuantity).sum() > quantity);
    }
}
