package com.example.projet_restaurant_digitalcity.pl.models.dto;

import com.example.projet_restaurant_digitalcity.domain.WorkerRoles;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class WorkerDTO {

    private long id;
    private String name;
    private String username;
    private WorkerRoles role;

}
