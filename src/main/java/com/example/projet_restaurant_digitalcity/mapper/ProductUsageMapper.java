package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.bl.services.ProductionService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ProductUsageDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.ProductUsageForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",uses = {ProductTemplateService.class, ProductionService.class})
public interface ProductUsageMapper {


    @Mapping(source = "idproductTemplate.name",target = "nameOfProductUsage")
    ProductUsageDTO toDto(ProductUsage productUsages);


    @Mapping(source = "form.nameProductTemplate",target = "idproductTemplate")
    @Mapping(source = "idProduction",target = "idproductionTemplate")
    ProductUsage toEntity(ProductUsageForm form,Long idProduction);

    @Named("listOfProductUsageToListOfDto")
     default List<ProductUsageDTO> listOfProductUsageToListOfDto(List<ProductUsage> productUsages){
        List<ProductUsageDTO> productUsagesDTO = new ArrayList<>();
        for (ProductUsage produit:productUsages) {
            productUsagesDTO.add(toDto(produit));
        }
        return productUsagesDTO;
    }



}
