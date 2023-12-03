package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StorageService {

    Page<Storage> getAll(int page , int countByPage);

    Storage getOneById(long storageId);

    Storage getOneByName(String name);

    Storage create(Storage storageToCreate);

    void delete(long idStorageToDelete);

    Storage update(long idStorageToUpdate,Storage toUpdate);

}
