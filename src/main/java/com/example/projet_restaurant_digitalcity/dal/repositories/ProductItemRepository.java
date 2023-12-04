package com.example.projet_restaurant_digitalcity.dal.repositories;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductTemplate;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductionItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {

    @Query("SELECT p FROM ProductItem p WHERE p.storage.id = :id ")
    Page<ProductItem> findAllByStorage(long id,Pageable pageable);

    @Query("SELECT p FROM ProductItem p WHERE p.storage.id = :id ")
    List<ProductItem> findAllByStorage(long id);

    List<ProductItem> findAllByProductTemplate(ProductTemplate productTemplate);

    @Query("""
        SELECT pi
        FROM ProductItem pi
        WHERE pi.productTemplate.name = :productName AND
            pi.expireDate >= current_date()
        ORDER BY pi.expireDate ASC
        LIMIT 1
    """)
    Optional<ProductItem> findOldestWithName(String productName);





}
