package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.ProductItemService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.mapper.ProductItemMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductItemDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductItemForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productitem")
public class ProductItemController {

    private final  ProductItemService productItemService;
    private final ProductItemMapper productItemMapper;

    public ProductItemController(ProductItemService productItemService, ProductItemMapper productItemMapper) {
        this.productItemService = productItemService;
        this.productItemMapper = productItemMapper;
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductItemDTO> getOneById(@PathVariable("id") long id){
        return ResponseEntity.ok(
                productItemMapper.toDto(productItemService.getOneById(id))
        );
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity<Page<ProductItemDTO>> getALL(@RequestParam int page, @RequestParam int countByPage){
        return ResponseEntity.ok(
                productItemService.getALL(page,countByPage)
                        .map(productItemMapper::toDto)
        );
    }

    @GetMapping("/all/{id:^[0-9]+$}")
    public ResponseEntity<Page<ProductItemDTO>> getProductItemsInStorage(@PathVariable("id") long storageId, @RequestParam int page) {
        Page<ProductItemDTO> productItemInStorageDTOS = productItemService.getProductInStorage(storageId,page,5).map(productItemMapper::toDto);
        return ResponseEntity.ok(productItemInStorageDTOS);
    }

    @PostMapping()
    public ResponseEntity<ProductItemDTO> addProductItemInStorage( @RequestBody @Valid ProductItemForm form) {
        productItemService.addProductInStorage(form.getStorageId(), productItemMapper.toEntity(form));

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> deleteProductItem(@PathVariable("id") long idProductToDelete) {
        productItemService.delete(idProductToDelete);
        return ResponseEntity.noContent()
                .build();

    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> updateProductItem(@PathVariable("id") long idProductItem,@RequestBody @Valid ProductItemForm form){
        ProductItem update = productItemService.update(idProductItem,productItemMapper.toEntity(form));

        return ResponseEntity.ok(
                productItemMapper.toDto(update)
        );

    }
}


