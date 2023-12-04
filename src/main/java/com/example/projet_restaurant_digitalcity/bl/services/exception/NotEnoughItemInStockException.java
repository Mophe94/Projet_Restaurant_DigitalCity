package com.example.projet_restaurant_digitalcity.bl.services.exception;

import lombok.Getter;

@Getter
public class NotEnoughItemInStockException extends RuntimeException {

    private final String nameItem;

    public NotEnoughItemInStockException(String nameItem){
        super("not enough %s in stock".formatted(nameItem));
        this.nameItem = nameItem;
    }

}
