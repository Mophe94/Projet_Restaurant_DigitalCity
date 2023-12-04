package com.example.projet_restaurant_digitalcity.bl.services.exception;

import lombok.Getter;

@Getter
public class UsernameNotFoundException extends RuntimeException {

    private final Class<?> resourceClass;
    private final Object username;

    public UsernameNotFoundException(Class<?> resourceClass, Object username) {
            super("%s is already use with Username : {%s}".formatted(resourceClass.getSimpleName(),username.toString()));
            this.resourceClass = resourceClass;
            this.username = username;
    }
}
