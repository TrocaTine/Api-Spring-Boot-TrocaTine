package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

public record AddingTrocadinhaRequestDTO(
        @NotNull
        String email,
        @NotNull
        int amountTrocadinha
) {
}
