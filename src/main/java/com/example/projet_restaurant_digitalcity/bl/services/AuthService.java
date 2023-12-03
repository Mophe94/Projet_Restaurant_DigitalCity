package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.Worker;



public interface AuthService {

    Worker login(String username, String password);
}
