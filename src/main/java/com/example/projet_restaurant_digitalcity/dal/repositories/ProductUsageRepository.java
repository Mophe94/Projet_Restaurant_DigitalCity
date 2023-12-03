package com.example.projet_restaurant_digitalcity.dal.repositories;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductUsage;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductUsageRepository extends JpaRepository<ProductUsage,Long> {


    ProductUsage findByIdproductionTemplateAndIdproductTemplate(ProductionTemplate idproductionTemplate, ProductTemplate idproductTemplate);

}
