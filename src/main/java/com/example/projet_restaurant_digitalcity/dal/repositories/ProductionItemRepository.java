package com.example.projet_restaurant_digitalcity.dal.repositories;

import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionItemRepository extends JpaRepository<ProductionItem,Long> {
}
