package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.ProductUsageService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import com.example.projet_restaurant_digitalcity.mapper.ProductUsageMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductUsageDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductUsageForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productusage")
public class ProductUsageController {

    private final ProductUsageService productUsageService;
    private final ProductUsageMapper productUsageMapper;
    private final ProductionService productionService;

    public ProductUsageController(ProductUsageService productUsageService, ProductUsageMapper productUsageMapper, ProductionService productionService) {
        this.productUsageService = productUsageService;
        this.productUsageMapper = productUsageMapper;
        this.productionService = productionService;
    }

    @PostMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> addProductUsageForOneProduction(@PathVariable("id") long idProductionTemplate, @RequestBody @Valid List<ProductUsageForm> productUsageForms){
       List<ProductUsage> productUsages =  productUsageForms.stream()
                .map(product -> productUsageMapper.toEntity(product,idProductionTemplate))
               .toList();
       productUsageService.addProductUsageListForProductionTemplate(productUsages,idProductionTemplate);

       return ResponseEntity.status(HttpStatus.CREATED)
               .build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> deleteProductUsageForOneProduction(@PathVariable("id") long idProductionTemplate,@RequestParam String nameProductToDelete){
        productUsageService.deleteProductUsageInProduction(nameProductToDelete,idProductionTemplate);
        return ResponseEntity.noContent()
                .build();

    }
}
