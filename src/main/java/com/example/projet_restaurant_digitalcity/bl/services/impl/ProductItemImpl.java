package com.example.projet_restaurant_digitalcity.bl.services.impl;
import com.example.projet_restaurant_digitalcity.bl.services.ProductItemService;
import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.bl.services.exception.ResourceNotFoundException;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import org.hibernate.mapping.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductItemImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;
    private final StorageService storageService;

    public ProductItemImpl(ProductItemRepository productItemRepository,StorageService storageService) {
        this.productItemRepository = productItemRepository;
        this.storageService = storageService;
    }

    public ProductItem getOneById(long productItemId){
        return productItemRepository.findById(productItemId)
                .orElseThrow(()-> new ResourceNotFoundException(ProductItem.class,productItemId));

    }

    @Override
    public Page<ProductItem> getALL(int page, int countByPage) {
        return productItemRepository.findAll(PageRequest.of(page,countByPage));
    }

    @Override
    public Page<ProductItem> getProductInStorage(long storageId, int page, int countByPage) {
        return productItemRepository.findAllByStorage(storageId, PageRequest.of(page,countByPage));
    }

    @Override
    public ProductItem addProductInStorage(long storageId, ProductItem productToAdd) {
        productToAdd.setStorage(storageService.getOneById(storageId));
        return productItemRepository.save(productToAdd);
    }

    @Override
    public void delete(long idProductToDelete) {
        productItemRepository.deleteById(idProductToDelete);
    }

    @Override
    public ProductItem update(long idProductToUpdate, ProductItem toUpdate) {
        if (!productItemRepository.existsById(idProductToUpdate))
            throw new ResourceNotFoundException(ProductItem.class,idProductToUpdate);

        toUpdate.setId(idProductToUpdate);
        return productItemRepository.save(toUpdate);
    }

}
