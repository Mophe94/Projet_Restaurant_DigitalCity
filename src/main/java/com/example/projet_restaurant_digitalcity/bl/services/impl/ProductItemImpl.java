package com.example.projet_restaurant_digitalcity.bl.services.impl;
import com.example.projet_restaurant_digitalcity.bl.services.ProductItemService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import org.springframework.stereotype.Service;

@Service
public class ProductItemImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;

    public ProductItemImpl(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public ProductItem getOneById(long productItemId){
        return productItemRepository.findById(productItemId)
                .orElseThrow(()-> new RuntimeException("no ProductItem with this id"));

    }

}
