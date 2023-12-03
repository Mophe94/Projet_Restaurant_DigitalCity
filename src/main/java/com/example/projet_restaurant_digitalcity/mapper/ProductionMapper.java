package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductionTemplateDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductionTemplateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {ProductUsageMapper.class, ProductTemplateService.class, ProductionService.class})
public interface ProductionMapper {

    @Mapping(source = "productTemplateResult.name", target = "productResult")
    @Mapping(source = "productUsed",target = "productUsedList" ,qualifiedByName = "listOfProductUsageToListOfDto")
    ProductionTemplateDTO toDto(ProductionTemplate productionTemplate);




    ProductionTemplate toEntity (ProductionTemplateForm form);






}
