package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import com.example.projet_restaurant_digitalcity.pl.models.dto.WorkerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductionItemMapper.class})
public interface WorkerMapper {


    @Mapping(source = "productionItems",target = "productionItemDTOList")
    WorkerDTO toDto(Worker worker);
}
