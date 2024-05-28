package com.bank.ams.controller;

import com.bank.ams.dto.ErrorResponse;
import com.bank.ams.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getCode())
                .body(new ErrorResponse(
                        e.getCode(),
                        e.getMessage()
                        )
                );
    }
}