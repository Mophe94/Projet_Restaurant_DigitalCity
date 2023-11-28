package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductTemplateRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.SupplierRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;
    private final SupplierRepository supplierRepository;



    public ProductTemplateServiceImpl(ProductTemplateRepository productTemplateRepository, SupplierRepository supplierRepository) {
        this.productTemplateRepository = productTemplateRepository;
        this.supplierRepository = supplierRepository;
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

        Supplier supplier = supplierRepository.findOneById(idSupplier)
                .orElseThrow(() -> new RuntimeException("no supplier found with this id"));

        productTemplate.setSupplier(supplier);


        return productTemplateRepository.save(productTemplate);
    }

}
