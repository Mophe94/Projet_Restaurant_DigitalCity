package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.OrderProductService;
import com.example.projet_restaurant_digitalcity.dal.repositories.OrderProductRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.OrderProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProduct findOneById(long id){
        return  orderProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no order find with this id "));
    }


}
