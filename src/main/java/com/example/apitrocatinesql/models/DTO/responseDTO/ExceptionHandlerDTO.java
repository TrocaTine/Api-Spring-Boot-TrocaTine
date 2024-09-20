package com.example.apitrocatinesql.models.DTO.responseDTO;

public record ExceptionHandlerDTO(
        int code,
        String message,
        String path
) {
}
