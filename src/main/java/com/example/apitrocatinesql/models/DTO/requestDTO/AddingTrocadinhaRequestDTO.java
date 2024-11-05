package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for adding a Trocadinha")
public record AddingTrocadinhaRequestDTO(

        @NotNull
        @Schema(description = "User's email address", example = "user@example.com")
        String email,

        @NotNull
        @Schema(description = "Amount of Trocadinhas to be added", example = "10")
        int amountTrocadinha
) {
}
