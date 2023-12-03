package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.StorageService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductItemDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductItemForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductTemplateService.class, StorageService.class})
public interface ProductItemMapper {

    @Mapping(target = "productTemplate",source = "nameProductTemplate")
    @Mapping(target = "storage",source = "storageId")
    ProductItem toEntity(ProductItemForm productItemForm);


    @Mapping(target = "productTemplateName",source = "productTemplate.name")
    ProductItemDTO toDto(ProductItem productItem);
}
