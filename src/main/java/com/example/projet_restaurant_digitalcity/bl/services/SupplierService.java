package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import org.springframework.data.domain.Page;

public interface SupplierService {

    Supplier getOneById(long id);

    Supplier findOneByName(String name);

    Page<Supplier> getAll(int page,int countByPage);

    Supplier create(Supplier toCreate);

    Supplier update(Supplier supplierToUpdate, long idSupplierToUpdate);

    void delete (long id);


}
