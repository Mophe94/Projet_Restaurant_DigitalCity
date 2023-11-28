package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import com.example.projet_restaurant_digitalcity.mapper.SupplierMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.SupplierDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.SupplierForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/supplier")
public class SupplierConstroller {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    public SupplierConstroller(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }


    @GetMapping(path = {"","/all"})
    public ResponseEntity<Page<SupplierDTO>> getAll(@RequestParam int page,@RequestParam int countByPage){

        Page<SupplierDTO> suppliers = supplierService.getAll(page, countByPage).map(supplierMapper::toDto);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SupplierDTO> getOneById(@PathVariable("id") long supplierId){
        return ResponseEntity.ok(
                supplierMapper.toDto(supplierService.getOneById(supplierId))
        );
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody @Valid SupplierForm form){
        supplierService.create(supplierMapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> update(@PathVariable("id") long idSupplier,@RequestBody @Valid SupplierForm form){
        Supplier update = supplierService.update(supplierMapper.toEntity(form),idSupplier);

        return ResponseEntity.ok(
                supplierMapper.toDto(update)
        );
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> delete (@PathVariable("id") long idSupplierToDelete){
        supplierService.delete(idSupplierToDelete);
        return ResponseEntity.noContent()
                .build();
    }

}
