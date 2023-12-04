package com.example.projet_restaurant_digitalcity.bl.services.exception;

import com.example.projet_restaurant_digitalcity.domain.ProductionStatus;
import com.example.projet_restaurant_digitalcity.domain.entity.ProductItem;
import lombok.Getter;

@Getter
public class CheckStatutProductionException extends RuntimeException {

    private final ProductionStatus status;

    public CheckStatutProductionException(ProductionStatus status){
        super("production is already with statut : %s)".formatted(status.toString()));
        this.status = status;
    }

}
