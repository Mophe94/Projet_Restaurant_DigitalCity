package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.AuthService;
import com.example.projet_restaurant_digitalcity.domain.entity.Role;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import com.example.projet_restaurant_digitalcity.pl.models.dto.AuthDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.LoginForm;
import com.example.projet_restaurant_digitalcity.utils.jwt.JWTProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JWTProvider jwtProvider;

    public AuthController(AuthService authService, JWTProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm form){
        Worker connectedUser = authService.login(form.getUsername(), form.getPassword());
        String token = jwtProvider.generateToken( connectedUser );
        return ResponseEntity.ok(
                new AuthDTO(
                        form.getUsername(),
                        token,
                        connectedUser.getRoles().stream()
                                .map(Role::getName)
                                .toList()
                )
        );
    }

}
