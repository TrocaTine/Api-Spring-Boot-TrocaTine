package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for requesting the retire of 'trocadinhas'")
public record RetiredTrocadinhaRequestDTO(
        @NotNull(message = "Email cannot be null.")
        @Schema(description = "User's email address associated with the trocadinha", example = "john.doe@example.com")
        String email,

        @NotNull(message = "Amount of trocadinha cannot be null.")
        @Schema(description = "The amount of trocadinha to be withdrawn", example = "5")
        int amountTrocadinha
) {
}
