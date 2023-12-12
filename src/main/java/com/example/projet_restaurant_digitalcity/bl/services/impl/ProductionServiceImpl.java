package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.*;
import com.example.projet_restaurant_digitalcity.bl.services.exception.CheckStatutProductionException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NameAlreadyUseException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NotEnoughItemInStockException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.ResourceNotFoundException;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionTemplateRepository;
import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import com.example.projet_restaurant_digitalcity.domain.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException(ProductionTemplate.class,id));
    }

    @Override
    public Page<ProductionTemplate> getAll(int page, int countByPage) {
        return productionTemplateRepository.findAll(PageRequest.of(page, countByPage));
    }

    @Override
    public ProductionTemplate create(ProductionTemplate toCreate) {
        if (productionTemplateRepository.existsByName(toCreate.getName())) {
            throw new NameAlreadyUseException(ProductionTemplate.class,toCreate.getName());
        }
        toCreate.setProductTemplateResult(productTemplateService.createFromProductionTemplate(toCreate));
        return productionTemplateRepository.save(toCreate);
    }

    @Override
    public ProductionTemplate update(long idProductionToUpdate, ProductionTemplate toUpdate) {
        if (!productionTemplateRepository.existsById(idProductionToUpdate))
            throw new ResourceNotFoundException(ProductionTemplate.class,idProductionToUpdate);


        List<ProductUsage> productUsages = productionTemplateRepository.findById(idProductionToUpdate).get().getProductUsed();

        toUpdate.setId(idProductionToUpdate);
        toUpdate.setProductUsed(productUsages);
        return productionTemplateRepository.save(toUpdate);
    }

    @Override
    public void delete(long id) {
        productionTemplateRepository.deleteById(id);
    }

    @Override
    public Page<ProductionItem> getAllWithStatutInProgress(int page, int countByPage) {
        return productionItemRepository.findAllByStatusContaining(ProductionStatus.IN_PROGRESS,PageRequest.of(page, countByPage));
    }

    @Override
    public Page<ProductionItem> getAllWithStatutFinish(int page, int countByPage) {
        return productionItemRepository.findAllByStatusContaining(ProductionStatus.FINISH,PageRequest.of(page,countByPage));
    }
    public Page<ProductionItem> getAllWithStatutFailed(int page, int countByPage) {
        return productionItemRepository.findAllByStatusContaining(ProductionStatus.FAILED,PageRequest.of(page,countByPage));
    }

    @Override
    public ProductionItem startProduction(long idProductionTemplate, int ratio, String nameWorker) {
        if (!productionTemplateRepository.existsById(idProductionTemplate))
            throw new ResourceNotFoundException(ProductionTemplate.class,idProductionTemplate);

        List<ProductUsage> productUse = productionTemplateRepository.findById(idProductionTemplate)
                .orElseThrow(()-> new ResourceNotFoundException(ProductionTemplate.class,idProductionTemplate))
                .getProductUsed();


        for (ProductUsage product : productUse) {
            if (!checkProductInStock(product.getIdproductTemplate(),product.getQuantity()))
                throw new NotEnoughItemInStockException(product.getIdproductTemplate().getName());

            removeProductInStorage(product.getQuantity()*ratio,product.getIdproductTemplate());
        }

        ProductionItem toCreate = new ProductionItem();
        toCreate.setId(0);
        toCreate.setStatus(ProductionStatus.IN_PROGRESS);
        toCreate.setProductionTemplate(productionTemplateRepository.findById(idProductionTemplate)
                .orElseThrow(() -> new ResourceNotFoundException(ProductionTemplate.class,idProductionTemplate)));
        toCreate.setRatio(ratio);
        toCreate.setWorker(workerService.findByName(nameWorker));

        productionItemRepository.save(toCreate);

        return toCreate;
    }

    @Override
    public ProductionItem pauseProduction(long idProductionItem) {
        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(() -> new ResourceNotFoundException(ProductionTemplate.class,idProductionItem));
        productionItem.setStatus(ProductionStatus.ON_PAUSE);
        productionItemRepository.save(productionItem);
        return productionItem;
    }

    @Override
    public ProductionItem unPauseProduction(long idProductionItem) {
        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(() -> new ResourceNotFoundException(ProductionTemplate.class,idProductionItem));
        productionItem.setStatus(ProductionStatus.IN_PROGRESS);
        productionItemRepository.save(productionItem);
        return productionItem;
    }

    @Override
    public void errorDuringProduction(long idProductionItem, List<ProductItem> productUsed) {
        if (!productionItemRepository.existsById(idProductionItem))
            throw new ResourceNotFoundException(ProductionTemplate.class,idProductionItem);

        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new ResourceNotFoundException(ProductionTemplate.class,idProductionItem));
        if (productionItem.getStatus() == ProductionStatus.FAILED)
            throw new CheckStatutProductionException(ProductionStatus.FAILED);

        for (ProductItem product: productUsed) {

        productItemService.addProductInStorage(
                product.getStorage().getId(),
                product);
        }

        ProductionItem production = productionItemRepository.findById(idProductionItem)
                .orElseThrow(() -> new ResourceNotFoundException(ProductionTemplate.class,idProductionItem));
        production.setStatus(ProductionStatus.FAILED);
        productionItemRepository.save(production);
    }

    @Override
    public void finishProduction(long idProductionItem, long idStorageToStoreResult, LocalDate expireDateItemResult) {
        if (!productionItemRepository.existsById(idProductionItem))
            throw new ResourceNotFoundException(ProductionTemplate.class,idProductionItem);

        if(productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new ResourceNotFoundException(ProductionItem.class,idProductionItem))
                .getStatus() == ProductionStatus.FINISH)
            throw new CheckStatutProductionException(ProductionStatus.FINISH);

        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new ResourceNotFoundException(ProductionTemplate.class,idProductionItem));
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


    @Override
    public void removeProductInStorage(double quantity, ProductTemplate productUsed) {

        while (quantity > 0) {

            ProductItem oldestProductItem = productItemRepository.findOldestWithName(productUsed.getName())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductItem.class,productUsed.getName()));

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

        return (productInStock.stream().mapToDouble(ProductItem::getQuantity).sum() >= quantity);
    }
}
