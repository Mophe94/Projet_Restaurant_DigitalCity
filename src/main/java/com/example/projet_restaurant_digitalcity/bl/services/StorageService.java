package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;

import java.util.List;

public interface StorageService {

    List<ProductItem> getProductInStorage(long storageId);

    ProductItem addProductInStorage(long storageId,ProductItem productToAdd);

    void deleteProductInStorage(long idProductToDelete);

    List<Storage> getAll();

    Storage getOneById(long storageId);

    Storage create(Storage storageToCreate);

    void delete(long idStorageToDelete);

    Storage update(long idStorageToUpdate,Storage toUpdate);

}
