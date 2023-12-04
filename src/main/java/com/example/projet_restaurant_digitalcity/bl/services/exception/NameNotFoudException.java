package com.example.projet_restaurant_digitalcity.bl.services.exception;

import lombok.Getter;

@Getter
public class NameNotFoudException extends RuntimeException {

        private final Class<?> resourceClass;
        private final Object name;

        public NameNotFoudException(Class<?> resourceClass, Object name) {
            super("%s could not be found with Name {%s}".formatted(resourceClass.getSimpleName(), name.toString()));
            this.resourceClass = resourceClass;
            this.name = name;
        }
}
