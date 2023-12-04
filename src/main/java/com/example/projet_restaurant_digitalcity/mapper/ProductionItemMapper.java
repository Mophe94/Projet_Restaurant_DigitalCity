package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductionItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductionItemMapper {

    @Mapping(source = "productionTemplate.name",target = "NameOfProduction")
    @Mapping(source = "worker.name", target = "nameOfWorker")
    ProductionItemDTO toDto(ProductionItem productionItem);


}
