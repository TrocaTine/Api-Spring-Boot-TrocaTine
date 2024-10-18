package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

public record SaveFavoriteProductRequestDTO(

        @NotNull
        String email,
        @NotNull
        Long id
) {
}
