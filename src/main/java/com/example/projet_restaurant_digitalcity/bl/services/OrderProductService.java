package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.OrderProduct;

import java.util.List;

public interface OrderProductService {

    OrderProduct findOneById(long id);
}
