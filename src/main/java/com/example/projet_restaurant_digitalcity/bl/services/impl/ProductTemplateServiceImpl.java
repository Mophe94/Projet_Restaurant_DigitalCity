package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductTemplateRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;
    private final SupplierService supplierService;



    public ProductTemplateServiceImpl(ProductTemplateRepository productTemplateRepository, SupplierService supplierService) {
        this.productTemplateRepository = productTemplateRepository;

        this.supplierService = supplierService;
    }

    public ProductTemplate getOneById(long id){
        return productTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no item found with this id"));
    }

    @Override
    public ProductTemplate getOneByName(String name) {

        return  productTemplateRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("no item found with this name"));
    }

    public Page<ProductTemplate> getAll(int page , int countByPage){
        return productTemplateRepository.findAll(PageRequest.of(page,countByPage));
    }

    public ProductTemplate create(ProductTemplate toCreate){

        if(productTemplateRepository.existsByName(toCreate.getName()))
            throw  new RuntimeException("this Name is already use take an oder one");

        return productTemplateRepository.save(toCreate);

    }
    public  ProductTemplate createFromProductionTemplate(ProductionTemplate toCreate){
        if(productTemplateRepository.existsByName(toCreate.getName()))
            throw  new RuntimeException("this Name is already use take an oder one");

        ProductTemplate productTemplateFromProduction = new ProductTemplate();
        productTemplateFromProduction.setPrice(toCreate.getPriceItemResult());
        productTemplateFromProduction.setName(toCreate.getName());
        productTemplateFromProduction.setOrigin("Production");
        productTemplateFromProduction.setMeasuringUnit(toCreate.getMeasuringUnit());
        return productTemplateRepository.save(productTemplateFromProduction);
    }

    public ProductTemplate update(long id,ProductTemplate toUpdate){
        if(!productTemplateRepository.existsById(id))
            throw new RuntimeException("no item found with this id");

        toUpdate.setId(id);
        return productTemplateRepository.save(toUpdate);
    }
    public void delete(long id){
        productTemplateRepository.deleteById(id);
    }

    @Override
    public ProductTemplate setSupplierToProduct(long idSupplier, long idProduct) {

        ProductTemplate productTemplate = productTemplateRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("no product found with this id"));

        Supplier supplier = supplierService.getOneById(idSupplier);

        productTemplate.setSupplier(supplier);


        return productTemplateRepository.save(productTemplate);
    }

}
