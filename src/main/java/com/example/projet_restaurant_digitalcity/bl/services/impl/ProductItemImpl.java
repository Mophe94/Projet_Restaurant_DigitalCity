package com.example.projet_restaurant_digitalcity.bl.services.impl;
import com.example.projet_restaurant_digitalcity.bl.services.ProductItemService;
import com.example.projet_restaurant_digitalcity.dal.repositories.ProductItemRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.StorageRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductItemImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;
    private final StorageRepository storageRepository;
    public ProductItemImpl(ProductItemRepository productItemRepository, StorageRepository storageRepository) {
        this.productItemRepository = productItemRepository;
        this.storageRepository = storageRepository;
    }

    public ProductItem getOneById(long productItemId){
        return productItemRepository.findById(productItemId)
                .orElseThrow(()-> new RuntimeException("no ProductItem with this id"));

    }

    @Override
    public Page<ProductItem> getALL(int page, int countByPage) {
        return productItemRepository.findAll(PageRequest.of(page, countByPage));
    }

    @Override
    public Page<ProductItem> getProductInStorage(long storageId, int page, int countByPage) {
        return productItemRepository.findAllByStorage(storageId, PageRequest.of(page,countByPage));
    }

    @Override
    public ProductItem addProductInStorage(long storageId, ProductItem productToAdd) {
        productToAdd.setStorage(storageRepository.findById(storageId)
                .orElseThrow(() -> new RuntimeException("no storage found with this id")));
        return productItemRepository.save(productToAdd);

    }
    public void deleteProductItemInStorage(long idProductToDelete){

    }

    @Override
    public void delete(long idProductToDelete) {
        productItemRepository.deleteById(idProductToDelete);
    }

    @Override
    public ProductItem update(long idProductToUpdate, ProductItem toUpdate) {
        if (!productItemRepository.existsById(idProductToUpdate))
            throw new RuntimeException("no storage found with this id");

        toUpdate.setId(idProductToUpdate);
        return productItemRepository.save(toUpdate);
    }


}
