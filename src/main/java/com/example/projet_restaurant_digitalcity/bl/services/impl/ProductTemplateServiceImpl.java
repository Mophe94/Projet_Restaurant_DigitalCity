package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductTemplateRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;

    public ProductTemplateServiceImpl(ProductTemplateRepository productTemplateRepository) {
        this.productTemplateRepository = productTemplateRepository;
    }

    public ProductTemplate getOne(long id){
        return productTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no item found with this id"));
    }

    public List<ProductTemplate> getAll(){
        return productTemplateRepository.findAll();
    }

    public ProductTemplate create(ProductTemplate toCreate){
        if(Objects.equals(toCreate.getName(), productTemplateRepository.findByName()))
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


}
