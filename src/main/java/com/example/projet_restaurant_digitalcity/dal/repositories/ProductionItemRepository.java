package com.example.projet_restaurant_digitalcity.dal.repositories;

import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductionItemRepository extends JpaRepository<ProductionItem,Long> {


    @Query("SELECT p FROM ProductionItem p WHERE p.status= :productionStatus ")
    Page<ProductionItem> findAllByStatusContaining(ProductionStatus productionStatus, Pageable pageable);

//    @Query("SELECT p FROM ProductionItem p WHERE p.status= :status" )
//    Page<ProductionItem> findAllByStatusContaining(ProductionStatus status, Pageable pageable);
}
