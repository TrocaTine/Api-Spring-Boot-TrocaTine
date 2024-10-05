package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SavePushRequestDTO(

        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        List<String> email
) {
}
