//package com.example.projet_restaurant_digitalcity.mapper;
//
//import com.example.projet_restaurant_digitalcity.bl.services.SupplierService;
//import com.example.projet_restaurant_digitalcity.domain.entity.Supplier;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Mapper(
//        componentModel = "spring"
//)
//public interface class ConvertSupplierId {
//
//    @Autowired
//    protected SupplierService supplierService;
//
//
//    @Mappings(
//            @Mapping(target = "id",expression = "java(supplierService.getById(supplierId))")
//    )
//    public abstract Supplier supplierIdToSupplier(Long supplierId);
//
//}
//
