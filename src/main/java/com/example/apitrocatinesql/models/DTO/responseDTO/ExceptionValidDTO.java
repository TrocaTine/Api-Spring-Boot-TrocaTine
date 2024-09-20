package com.example.apitrocatinesql.models.DTO.responseDTO;

import java.util.Map;

public record ExceptionValidDTO(
        int status,
        String message,
        Map<String, String>errors
) {
}
