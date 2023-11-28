package com.example.projet_restaurant_digitalcity.dal.repositories;

import com.example.projet_restaurant_digitalcity.domain.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {


}
