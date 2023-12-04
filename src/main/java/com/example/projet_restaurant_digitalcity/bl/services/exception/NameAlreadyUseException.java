package com.example.projet_restaurant_digitalcity.bl.services.exception;

import lombok.Getter;

@Getter
public class NameAlreadyUseException extends RuntimeException {

    private final Class<?> resourceClass;
    private final Object name;

    public NameAlreadyUseException(Class<?> resourceClass, Object name) {
        super("%s is already use with Name : {%s}".formatted(resourceClass.getSimpleName(), name.toString()));
        this.resourceClass = resourceClass;
        this.name = name;
    }
}
