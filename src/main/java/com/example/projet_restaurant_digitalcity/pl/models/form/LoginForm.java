package com.example.projet_restaurant_digitalcity.pl.models.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data

public class LoginForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
