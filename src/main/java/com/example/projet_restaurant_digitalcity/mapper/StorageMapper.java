package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import com.example.projet_restaurant_digitalcity.pl.models.dto.StorageDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.StorageForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StorageMapper {

    StorageDTO toDTo(Storage storage);
//    @Mapping(target = "productTemplateName",source = "productTemplate.name")
//    StorageDTO.ProductItemInStorageDTO productItemtoDto(ProductItem productItem);

    Storage toEntity(StorageForm storageForm);

}
