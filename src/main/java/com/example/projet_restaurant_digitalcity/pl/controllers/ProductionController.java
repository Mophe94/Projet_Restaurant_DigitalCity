package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import com.example.projet_restaurant_digitalcity.mapper.ProductItemMapper;
import com.example.projet_restaurant_digitalcity.mapper.ProductionItemMapper;
import com.example.projet_restaurant_digitalcity.mapper.ProductionMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductionItemDTO;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductionTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductUseForErrorProductionForm;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductionTemplateForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/production")
public class ProductionController {

    private final ProductionMapper productionMapper;
    private final ProductionService productionService;
    private final ProductionItemMapper productionItemMapper;

    private final ProductItemMapper productItemMapper;


    public ProductionController(ProductionMapper productionMapper, ProductionService productionService, ProductionItemMapper productionItemMapper,StorageService storageService, ProductItemMapper productItemMapper) {
        this.productionMapper = productionMapper;
        this.productionService = productionService;
        this.productionItemMapper = productionItemMapper;
        this.productItemMapper = productItemMapper;
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductionTemplateDTO> getOneById(@PathVariable("id") long idProductionTemplate) {
        return ResponseEntity.ok(
                productionMapper.toDto(productionService.getOneById(idProductionTemplate))
        );
    }

    @GetMapping(path = {"", "/all"})
    public ResponseEntity<Page<ProductionTemplateDTO>> getAll(@RequestParam int page, @RequestParam int countByPage) {
        return ResponseEntity.ok(
                productionService.getAll(page, countByPage)
                        .map(productionMapper::toDto)
        );
    }


    @PostMapping()
    public ResponseEntity<ProductionTemplateDTO> addProductionTemplate(@RequestBody @Valid ProductionTemplateForm toCreate) {

        productionService.create(productionMapper.toEntity(toCreate));


        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    ResponseEntity<ProductionTemplateDTO> updateProductionTemplate(@PathVariable("id") long idProduction,@RequestBody @Valid ProductionTemplateForm form){

        ProductionTemplate toUpdate = productionService.update(idProduction, productionMapper.toEntity(form));
        List<ProductUsage> check = toUpdate.getProductUsed();
        return ResponseEntity.ok(
                productionMapper.toDto(toUpdate)
        );
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> deleteProductionTemplate(@PathVariable("id") long idProductionTemplate){
        productionService.delete(idProductionTemplate);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/allinprogress")
    public ResponseEntity<Page<ProductionItemDTO>> getAllProductionInProgress(@RequestParam int page, @RequestParam int countByPage){
        return ResponseEntity.ok(
                productionService.getAllWithStatutInProgress(page, countByPage)
                        .map(productionItemMapper::toDto)
        );
    }
    @GetMapping("/allfinish")
    public ResponseEntity<Page<ProductionItemDTO>> getAllProductionFinish(@RequestParam int page, @RequestParam int countByPage){
        return ResponseEntity.ok(
                productionService.getAllWithStatutFinish(page, countByPage)
                        .map(productionItemMapper::toDto)
        );
    }
    @GetMapping("/allfailed")
    public ResponseEntity<Page<ProductionItemDTO>> getAllProductionFailed(@RequestParam int page, @RequestParam int countByPage){
        return ResponseEntity.ok(
                productionService.getAllWithStatutFailed(page, countByPage)
                        .map(productionItemMapper::toDto)
        );
    }

    @PostMapping("/start/{id:^[0-9]+$}")
    public ResponseEntity<?> startProduction(@PathVariable("id") long idProduction, @RequestParam int ratio, @RequestParam String nameWorker) {

        return ResponseEntity.ok(
                productionItemMapper.toDto(productionService.startProduction(idProduction, ratio, nameWorker))
        );
    }

    @PutMapping("/pause/{id:^[0-9]+$}")
    public ResponseEntity<?> pauseProduction(@PathVariable("id") long idProductionItem) {
        ProductionItem productionItem = productionService.pauseProduction(idProductionItem);
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
    public ResponseEntity<?> errorProduction(@PathVariable("id") long idProductionItem, @RequestBody @Valid List<ProductUseForErrorProductionForm> productUse){
            productionService.errorDuringProduction(
                    idProductionItem,
                    productUse.stream().map(productItemMapper::ErrorFormToProductItem).toList()
            );


        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/finish/{id:^[0-9]+$}")
    public ResponseEntity<?> finishProduction(@PathVariable("id") long idProductionItem, @RequestParam long idStorageToStoreResult, LocalDate expireDateItemResult){
         productionService.finishProduction(idProductionItem,idStorageToStoreResult,expireDateItemResult);

         return ResponseEntity.ok()
                 .build();
    }
}
