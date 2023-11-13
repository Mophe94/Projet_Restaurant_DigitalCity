package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductTemplateForm;
import org.mapstruct.Mapper;

@Mapper
public interface ProductTemplateMapper {

    ProductTemplateDTO toDto(ProductTemplate productTemplate);

    ProductTemplate toEntity(ProductTemplateForm productTemplateForm);
}
