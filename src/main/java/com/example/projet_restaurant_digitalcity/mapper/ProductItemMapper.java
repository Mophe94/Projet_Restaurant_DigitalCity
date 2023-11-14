package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductItemForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductTemplateService.class})
public interface ProductItemMapper {

    @Mapping(target = "productTemplate",source = "productTemplateId")
    ProductItem toEntity(ProductItemForm productItemForm);
}
