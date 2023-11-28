package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import com.example.projet_restaurant_digitalcity.mapper.StorageMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.StorageDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.StorageForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;
    private final StorageMapper storageMapper;


    public StorageController(
            StorageService storageService,
            StorageMapper storageMapper
    ) {
        this.storageService = storageService;
        this.storageMapper = storageMapper;
    }

    @GetMapping(path = {"", "all"})
    public ResponseEntity<Page<StorageDTO>> getAll(@RequestParam int page ) {
        Page<StorageDTO> storages = storageService.getAll(page,5).map(storageMapper::toDTo);
        return ResponseEntity.ok( storages );

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

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> delete(@PathVariable("id")long idStorageToDelete){
        storageService.delete(idStorageToDelete);

        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> update(@PathVariable("id") long idStorage,@RequestBody @Valid StorageForm form){
        Storage update = storageService.update(idStorage,storageMapper.toEntity(form));

        return ResponseEntity.ok(
                storageMapper.toDTo(update)
        );

    }
}
