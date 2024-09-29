package com.example.apitrocatinesql.exception;

public record ExceptionHandlerDTO(
        int code,
        String message,
        String path
) {
}
