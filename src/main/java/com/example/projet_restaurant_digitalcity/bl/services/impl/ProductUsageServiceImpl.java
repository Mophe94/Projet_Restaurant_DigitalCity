package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductUsageService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductUsageRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUsageServiceImpl implements ProductUsageService {

    private final ProductUsageRepository productUsageRepository;
    private final ProductionService productionService;
    private final ProductTemplateService productTemplateService;

    public ProductUsageServiceImpl(ProductUsageRepository productUsageRepository, ProductionService productionService, ProductTemplateService productTemplateService) {
        this.productUsageRepository = productUsageRepository;
        this.productionService = productionService;
        this.productTemplateService = productTemplateService;
    }
    @Override
    public List<ProductUsage> addProductUsageListForProductionTemplate(List<ProductUsage> productUsages,long idProductionTemplate) {
        for (ProductUsage product:productUsages) {
            product.setIdproductionTemplate(productionService.getOneById(idProductionTemplate));
            product.setId(new ProductUsage.ProductTemplateUseProductionTemplateId());
            productUsageRepository.save(product);
        }
        return productUsages;
    }

    @Override
    public void deleteProductUsageInProduction(String nameProduct, long idProduction) {
        ProductionTemplate production = productionService.getOneById(idProduction);
        ProductUsage toDelete = productUsageRepository.findByIdproductionTemplateAndIdproductTemplate(production,productTemplateService.getOneByName(nameProduct));
        productUsageRepository.delete(toDelete);
    }


}
