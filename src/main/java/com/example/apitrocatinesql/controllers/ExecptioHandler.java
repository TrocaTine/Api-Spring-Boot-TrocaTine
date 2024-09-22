package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.exception.ExceptionHandlerDTO;
import com.example.apitrocatinesql.exception.ExceptionValidDTO;
import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExecptioHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        StandardResponseDTO response = new StandardResponseDTO(true, new ExceptionValidDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                errors
        ));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundUser.class)
    public ResponseEntity<StandardResponseDTO> notFoundUserException(HttpServletRequest request) {
        StandardResponseDTO response = new StandardResponseDTO(true,
                new ExceptionHandlerDTO(404, "Not Found User", request.getServletPath()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



}

