package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.mapper.ProductionItemMapper;
import com.example.projet_restaurant_digitalcity.mapper.ProductionMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductionTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductUseForErrorProductionForm;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductionTemplateForm;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/production")
public class ProductionController {

    private final ProductionMapper productionMapper;
    private final ProductionService productionService;
    private final ProductionItemMapper productionItemMapper;
    private final ProductTemplateService productTemplateService;
    private final StorageService storageService;


    public ProductionController(ProductionMapper productionMapper, ProductionService productionService, ProductionItemMapper productionItemMapper, ProductTemplateService productTemplateService, StorageService storageService) {
        this.productionMapper = productionMapper;
        this.productionService = productionService;
        this.productionItemMapper = productionItemMapper;
        this.productTemplateService = productTemplateService;
        this.storageService = storageService;
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductionTemplateDTO>getOneById(@PathVariable("id") long idProductionTemplate){
        return ResponseEntity.ok(
            productionMapper.toDto(productionService.getOneById(idProductionTemplate))
        );
    }

    @GetMapping(path = {"","/all"})
    public ResponseEntity<Page<ProductionTemplateDTO>> getAll(@RequestParam int page, @RequestParam int countByPage){
        return ResponseEntity.ok(
                productionService.getAll(page, countByPage)
                        .map(productionMapper::toDto)
        );
    }
    @PostMapping()
    public ResponseEntity<ProductionTemplateDTO> addProductionTemplate(@RequestBody ProductionTemplateForm toCreate ){

        productionService.create(productionMapper.toEntity(toCreate));


        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/start/{id:^[0-9]+$}")
    public ResponseEntity<?> startProduction(@PathVariable("id") long idProduction,@RequestParam int ratio,@RequestParam String nameWorker){

        return ResponseEntity.ok(
                productionItemMapper.toDto(productionService.startProduction(idProduction,ratio,nameWorker))
        );
    }
    @PutMapping("/pause/{id:^[0-9]+$}")
    public ResponseEntity<?> pauseProduction(@PathVariable("id") long idProductionItem){
              ProductionItem productionItem =  productionService.pauseProduction(idProductionItem);
        return ResponseEntity.ok(
                productionItemMapper.toDto(productionItem)
        );
    }

    @PutMapping("/unpause/{id:^[0-9]+$}")
    public ResponseEntity<?> unPauseProduction(@PathVariable("id") long idProductionItem) {
        ProductionItem productionItem = productionService.unPauseProduction(idProductionItem);
        return ResponseEntity.ok(
                productionItemMapper.toDto(productionItem)
        );
    }
    @PostMapping("/error/{id:^[0-9]+$}")
    public ResponseEntity<?> errorProduction(@PathVariable("id") long idProductionItem, @RequestBody List<ProductUseForErrorProductionForm>productUse){
        for (ProductUseForErrorProductionForm product:productUse) {
        productionService.errorDuringProduction(
                idProductionItem,
                productTemplateService.getOneByName(product.getNameProductTemplate()),
                product.getQuantity(),
                product.getNameStorage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
