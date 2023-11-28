package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductTemplateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SupplierService.class})
public interface ProductTemplateMapper {

    @Mapping(source = "supplier.name",target = "supplierName")
    ProductTemplateDTO toDto(ProductTemplate productTemplate);


    ProductTemplate toEntity(ProductTemplateForm productTemplateForm);


}


