package com.example.projet_restaurant_digitalcity.pl.models.form;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class WorkerForm {

    String name;
    String username;
    String password;

}
