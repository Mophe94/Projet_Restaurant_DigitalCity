package com.example.projet_restaurant_digitalcity.pl.controllers;
import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.mapper.ProductTemplateMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductTemplateForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productTemplate")
public class ProductTemplateController {

    private final ProductTemplateService productTemplateService;
    private final ProductTemplateMapper productTemplateMapper;

    public ProductTemplateController(ProductTemplateService productTemplateService, ProductTemplateMapper productTemplateMapper) {
        this.productTemplateService = productTemplateService;

        this.productTemplateMapper = productTemplateMapper;
    }

    @GetMapping(path = {"","/all"})

    public ResponseEntity<Page<ProductTemplateDTO>> getAll(@RequestParam int page , @RequestParam int countByPage){
        Page<ProductTemplateDTO> productTemplateDTOS = productTemplateService.getAll(page,countByPage)
                .map(productTemplateMapper::toDto);
        return ResponseEntity.ok(productTemplateDTOS);
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
    @PatchMapping("{id:^[0-9]+$}")
    public ResponseEntity<?> addProductToSupplier(@PathVariable("id") long idProduct, @RequestParam long idSupplier){
        productTemplateService.setSupplierToProduct(idSupplier,idProduct);
        return ResponseEntity.ok(
                productTemplateMapper.toDto(productTemplateService.getOneById(idProduct))
        );

    }
}
