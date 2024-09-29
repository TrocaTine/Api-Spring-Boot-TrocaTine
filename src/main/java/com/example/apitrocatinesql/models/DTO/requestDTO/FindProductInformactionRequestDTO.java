package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

public record FindProductInformactionRequestDTO(
        @NotNull
        Long id
) {
}
