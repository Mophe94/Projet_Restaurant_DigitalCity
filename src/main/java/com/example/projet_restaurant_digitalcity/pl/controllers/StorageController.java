package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.mapper.ProductItemMapper;
import com.example.projet_restaurant_digitalcity.mapper.StorageMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.StorageDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductItemForm;
import com.example.projet_restaurant_digitalcity.pl.models.form.StorageForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;
    private final StorageMapper storageMapper;
    private final ProductItemMapper productItemMapper;

    public StorageController(
            StorageService storageService,
            StorageMapper storageMapper,
            ProductItemMapper productItemMapper
    ) {
        this.storageService = storageService;
        this.storageMapper = storageMapper;
        this.productItemMapper = productItemMapper;
    }

    @GetMapping("/allproductitem/{id:^[0-9]+$}")
    public ResponseEntity<List<StorageDTO.ProductItemInStorageDTO>> getProductItems(@PathVariable("id") long storageId) {
        return ResponseEntity.ok(
                storageService.getProductInStorage(storageId).stream()
                        .map(storageMapper::productItemtoDto)
                        .toList()
        );
    }

    @PostMapping("/addproduct/{id:^[0-9]+$}")
    public ResponseEntity<StorageDTO.ProductItemInStorageDTO> addProductInStorage(@PathVariable("id") long storageId, @RequestBody @Valid ProductItemForm form) {
        storageService.addProductInStorage(storageId, productItemMapper.toEntity(form));

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("deleteproduct/{id:^[0-9]+$}")
    public ResponseEntity<?> deleteProductInStorage(@RequestParam("id") long idProductToDelete) {
        storageService.deleteProductInStorage(idProductToDelete);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping(path = {"", "all"})
    public ResponseEntity<List<StorageDTO>> getAll() {
        return ResponseEntity.ok(
                storageService.getAll().stream()
                        .map(storageMapper::toDTo)
                        .toList()
        );
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<StorageDTO> getOneById(@PathVariable("id") long storageId) {
        return ResponseEntity.ok(
                storageMapper.toDTo(storageService.getOneById(storageId))
        );
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody @Valid StorageForm form) {
        storageService.create(storageMapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
