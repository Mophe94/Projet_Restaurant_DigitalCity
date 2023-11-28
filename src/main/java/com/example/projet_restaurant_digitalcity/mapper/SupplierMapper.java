package com.example.projet_restaurant_digitalcity.mapper;

import com.example.projet_restaurant_digitalcity.bl.services.ProductTemplateService;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
import com.example.projet_restaurant_digitalcity.pl.models.dto.SupplierDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.SupplierForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",uses = {ProductTemplateService.class})
public interface SupplierMapper {



    Supplier toEntity(SupplierForm form);



    @Mapping(target = "namesProductTemplates",source = "productTemplates" ,qualifiedByName = "productTemplatesToProductTemplateNames")
    SupplierDTO  toDto(Supplier supplier);


    @Named("productTemplatesToProductTemplateNames")
    default List<String> productTemplatesToProductTemplateNames(List<ProductTemplate> productTemplates){
        if (productTemplates == null){
            return null;
        }else {
            List<String> names = new ArrayList<>();
            for (ProductTemplate productTemplate: productTemplates) {
                names.add(productTemplate.getName());
            }
            return names;
        }

    }


}
