package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductItemDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductItemForm;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductUseForErrorProductionForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductTemplateService.class, StorageService.class})
public interface ProductItemMapper {

    @Mapping(target = "productTemplate",source = "nameProductTemplate")
    @Mapping(target = "storage",source = "storageId")
    ProductItem toEntity(ProductItemForm productItemForm);


    @Mapping(target = "productTemplateName",source = "productTemplate.name")
    @Mapping(target = "measuringUnit",source = "productTemplate.measuringUnit")
    ProductItemDTO toDto(ProductItem productItem);

    @Mapping(target = "productTemplate",source = "nameProductTemplate")
    @Mapping(target = "storage",source = "nameStorage")
    ProductItem ErrorFormToProductItem(ProductUseForErrorProductionForm form);
}
