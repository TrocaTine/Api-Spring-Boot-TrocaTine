package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.exception.*;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.fasterxml.jackson.core.JsonParseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExecptioHandler {

    @RestControllerAdvice
    public class ExceptionHandlerAdvice {

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
        public ResponseEntity<StandardResponseDTO> notFoundUserException(NotFoundUser nfu, HttpServletRequest request) {
            StandardResponseDTO response = new StandardResponseDTO(true,
                    new ExceptionHandlerDTO(404, nfu.getMessage(), request.getServletPath()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(NotFound.class)
        public ResponseEntity<StandardResponseDTO> notFoundException(NotFound nf, HttpServletRequest request) {
            StandardResponseDTO response = new StandardResponseDTO(true,
                    new ExceptionHandlerDTO(404, nf.getMessage(), request.getServletPath()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ForeignKeyConstraintException.class)
        public ResponseEntity<StandardResponseDTO> foreignKeyConstraintException(ForeignKeyConstraintException fkce, HttpServletRequest request) {
            StandardResponseDTO response = new StandardResponseDTO(true,
                    new ExceptionHandlerDTO(409, fkce.getMessage(), request.getServletPath()));
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(NotFoundCategory.class)
        public ResponseEntity<StandardResponseDTO> notFoundCategory(NotFoundCategory nfc, HttpServletRequest request) {
            StandardResponseDTO response = new StandardResponseDTO(true,
                    new ExceptionHandlerDTO(404, nfc.getMessage(), request.getServletPath()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(SelfProductAddition.class)
        public ResponseEntity<StandardResponseDTO> selfProductAddition(SelfProductAddition spa, HttpServletRequest request) {
            StandardResponseDTO response = new StandardResponseDTO(true,
                    new ExceptionHandlerDTO(403, spa.getMessage(), request.getServletPath()));
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }






    }

}

