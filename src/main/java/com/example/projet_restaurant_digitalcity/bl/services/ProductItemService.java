package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;


public interface  ProductItemService {

    ProductItem getOneById(long productItemId);

    //get all productItem in ALL Storage
    Page<ProductItem> getALL(int page , int countByPage);
    //get all productItem in One Storage
    Page<ProductItem> getProductInStorage(long storageId, int page, int countByPage);


    ProductItem addProductInStorage(long storageId,ProductItem productToAdd);

    void delete(long idProductToDelete);

    ProductItem update(long idProductToUpdate,ProductItem productItem);
}
