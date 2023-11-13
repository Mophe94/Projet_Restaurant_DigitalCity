package com.example.projet_restaurant_digitalcity.dal.repositories;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTemplateRepository extends JpaRepository<ProductTemplate,Long> {


    Optional<ProductTemplate> getFirstByName(String name);

    boolean existsByName(String name);


}
