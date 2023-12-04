package com.example.projet_restaurant_digitalcity.pl.advice;


import com.example.projet_restaurant_digitalcity.bl.services.exception.*;
import com.example.projet_restaurant_digitalcity.pl.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ControllerAdvisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }

    @ExceptionHandler(NameAlreadyUseException.class)
    public ResponseEntity<ErrorDTO> handle(NameAlreadyUseException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorDTO body = ErrorDTO.builder(uri,receivedAt,status.value())
                .putError("message",ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .build();
    }

    @ExceptionHandler(NameNotFoudException.class)
    public ResponseEntity<ErrorDTO> handle(NameNotFoudException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDTO body = ErrorDTO.builder(uri,receivedAt,status.value())
                .putError("message",ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(UsernameNotFoundException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDTO body = ErrorDTO.builder(uri,receivedAt,status.value())
                .putError("message",ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .build();
    }

    @ExceptionHandler(CheckStatutProductionException.class)
    public ResponseEntity<ErrorDTO> handle(CheckStatutProductionException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorDTO body = ErrorDTO.builder(uri,receivedAt,status.value())
                .putError("message",ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .build();
    }

    @ExceptionHandler(NotEnoughItemInStockException.class)
    public ResponseEntity<ErrorDTO> handle(NotEnoughItemInStockException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorDTO body = ErrorDTO.builder(uri,receivedAt,status.value())
                .putError("message",ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .build();
    }


}
