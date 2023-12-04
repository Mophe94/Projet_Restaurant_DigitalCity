package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NameAlreadyUseException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NameNotFoudException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.ResourceNotFoundException;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductTemplateRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.SupplierRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;


    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;

    }

    @Override
    public Supplier getOneById(long id) {
        return supplierRepository.findOneById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Supplier.class,id));

    }

    @Override
    public Supplier findOneByName(String name) {
        return supplierRepository.findByName(name)
                .orElseThrow(() -> new NameNotFoudException(Supplier.class,name));
    }

    @Override
    public Page<Supplier> getAll(int page, int countByPage) {
        return supplierRepository.findAll(PageRequest.of(page,countByPage));
    }

    @Override
    public Supplier create(Supplier toCreate) {
         if (supplierRepository.existsByName(toCreate.getName()))
             throw new NameAlreadyUseException(Supplier.class,toCreate.getName());

         return supplierRepository.save(toCreate);
    }

    @Override
    public Supplier update(Supplier supplierToUpdate, long idSupplierToUpdate) {
        if (!supplierRepository.existsById(idSupplierToUpdate))
            throw new ResourceNotFoundException(Supplier.class,idSupplierToUpdate);

        supplierToUpdate.setId(idSupplierToUpdate);
        return supplierRepository.save(supplierToUpdate);
    }

    @Override
    public void delete(long idSupplierToDelete) {
        supplierRepository.deleteById(idSupplierToDelete);
    }

}
