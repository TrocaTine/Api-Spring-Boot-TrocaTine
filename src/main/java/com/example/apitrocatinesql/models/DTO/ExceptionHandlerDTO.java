package com.example.apitrocatinesql.models.DTO;

public record ExceptionHandlerDTO(
        int code,
        String message,
        String path
) {
}
