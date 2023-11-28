package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductionTemplateRepository;
import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class ProductionServiceImpl implements ProductionService {

    private final ProductionTemplateRepository productionTemplateRepository;
    private final ProductionItemRepository productionItemRepository;

    public ProductionServiceImpl(ProductionTemplateRepository productionTemplateRepository, ProductionItemRepository productionItemRepository) {
        this.productionTemplateRepository = productionTemplateRepository;
        this.productionItemRepository = productionItemRepository;
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
        if (productionTemplateRepository.existsByName(toCreate.getName()))
            throw new RuntimeException("this name is already use");
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
    public List<ProductionItem> startProduction(long idProductionTemplate, int nbOfProduction,int quantityToStart) {
        if (!productionTemplateRepository.existsById(idProductionTemplate))
            throw new RuntimeException("no production found with this id");

        List<ProductionItem> productionItems = null;
        for (int i = 0; i < nbOfProduction ; i++) {
            ProductionItem toCreate = new ProductionItem();
            toCreate.setId(0);
            toCreate.setStatus(ProductionStatus.JUST_STARTED);
            toCreate.setProductionTemplate(productionTemplateRepository.findById(idProductionTemplate)
                    .orElseThrow(() -> new RuntimeException("no production found with this id")));


            productionItems.add(toCreate);
            productionItemRepository.save(toCreate);
            
        }

        return productionItems;
    }

    //save le statut
    @Override
    public ProductionItem pauseProduction(long idProductionItem) {
        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new RuntimeException("no production found with this id"));
        productionItem.setStatus(ProductionStatus.ON_PAUSE);
        productionItemRepository.save(productionItem);
        return productionItem;
    }

    @Override
    public ProductTemplate errorDuringProduction(long idProductionItem, ProductTemplate productAlreadyUsed, long idStorage) {
        if (!productionItemRepository.existsById(idProductionItem))
            throw new RuntimeException("this production don't exist");

        ProductionItem productionItem = productionItemRepository.findById(idProductionItem)
                .orElseThrow(()-> new RuntimeException("no production found wit this id"));

         List<ProductTemplate> allProductOfThisProduction = productionItem.getProductionTemplate().getProductTemplateList();

    return null;
    }

    @Override
    public ProductionItem finishProduction(long idProductionItem) {
        return null;
    }
}
