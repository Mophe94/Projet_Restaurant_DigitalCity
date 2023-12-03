package com.example.projet_restaurant_digitalcity.pl.models.dto;

import java.util.List;

public record AuthDTO (
        String username,
        String token,
        List<String> roles)
{}
