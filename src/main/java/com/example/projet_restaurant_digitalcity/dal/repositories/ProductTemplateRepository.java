package com.example.projet_restaurant_digitalcity.dal.repositories;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductTemplateRepository extends JpaRepository<ProductTemplate,Long> {

    Optional<ProductTemplate> findByName(String name);
    boolean existsByName(String name);


}
