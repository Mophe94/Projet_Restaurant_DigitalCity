package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NameAlreadyUseException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NameNotFoudException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.ResourceNotFoundException;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.StorageRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    public final StorageRepository storageRepository;


    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;

    }

    @Override
    public Page<Storage> getAll(int page, int countByPage) {

        return storageRepository.findAll(PageRequest.of(page,countByPage));
    }

    @Override
    public Storage getOneById(long storageId) {
        return storageRepository.findById(storageId)
                .orElseThrow(()-> new ResourceNotFoundException(Storage.class,storageId));
    }

    @Override
    public Storage getOneByName(String name) {
        return storageRepository.findByName(name)
                .orElseThrow(() -> new NameNotFoudException(Storage.class,name));
    }

    @Override
    public Storage create(Storage storageToCreate) {
        if (storageRepository.existsByName(storageToCreate.getName()))
            throw new NameAlreadyUseException(Storage.class,storageToCreate.getName());

        return storageRepository.save(storageToCreate);
    }

    @Override
    public void delete(long idStorageToDelete) {
        storageRepository.deleteById(idStorageToDelete);
    }

    @Override
    public Storage update(long idStorageToUpdate, Storage toUpdate) {
        if(!storageRepository.existsById(idStorageToUpdate))
            throw new ResourceNotFoundException(Storage.class,idStorageToUpdate);

        toUpdate.setId(idStorageToUpdate);
        return storageRepository.save(toUpdate);
    }
}
