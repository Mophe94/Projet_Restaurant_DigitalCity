package com.example.projet_restaurant_digitalcity.dal.repositories;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Long> {

    @Query("SELECT s.productItems FROM Storage s WHERE s.id = :id")
    List<ProductItem> findAllProductItem(Long id);

    Boolean existsByName(String name);



}
