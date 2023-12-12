package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.AuthService;
import com.example.projet_restaurant_digitalcity.dal.repositories.WorkerRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final WorkerRepository workerRepository;

    public AuthServiceImpl(AuthenticationManager authManager, WorkerRepository workerRepository) {
        this.authManager = authManager;

        this.workerRepository = workerRepository;
    }

    @Override
    public Worker login(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        auth = authManager.authenticate(auth);
        return workerRepository.findByUsername(auth.getName())
                .orElseThrow();
    }


}

