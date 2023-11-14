package com.example.projet_restaurant_digitalcity.pl.controllers;
import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.mapper.ProductTemplateMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductTemplateForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productTemplate")
public class ProductTemplateController {

    private final ProductTemplateService productTemplateService;
    private final SupplierService supplierService;
    private final ProductTemplateMapper productTemplateMapper;

    public ProductTemplateController(ProductTemplateService productTemplateService, SupplierService supplierService, ProductTemplateMapper productTemplateMapper) {
        this.productTemplateService = productTemplateService;
        this.supplierService = supplierService;
        this.productTemplateMapper = productTemplateMapper;
    }

    @GetMapping(path = {"","/all"})
    public ResponseEntity<List<ProductTemplateDTO>> getAll(){
        List<ProductTemplateDTO> body = productTemplateService.getAll().stream()
                .map(productTemplateMapper::toDto)
                .toList();

        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductTemplateDTO> getOneById(@PathVariable long id){
        return ResponseEntity.ok(
                productTemplateMapper.toDto(productTemplateService.getOneById(id))
        );
    }

    @GetMapping("/name")
    public ResponseEntity<ProductTemplateDTO> getOneByName(@RequestParam String name){
        return ResponseEntity.ok(
                productTemplateMapper.toDto(productTemplateService.getOneByName(name))
        );
    }

    @PostMapping(path = {"","/add"})
    public ResponseEntity<?> create(@Valid @RequestBody ProductTemplateForm form){
        ProductTemplate entity = productTemplateMapper.toEntity(form);
        entity.setSupplier( supplierService.getById(form.getSupplierId()) );
        productTemplateService.create(entity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> delete(@PathVariable long id){
        productTemplateService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> update(@PathVariable long id,@Valid @RequestBody ProductTemplateForm form){
       ProductTemplate update =  productTemplateService.update(id,productTemplateMapper.toEntity(form));
        return ResponseEntity.ok(
                productTemplateMapper.toDto(update)
        );
    }
}
