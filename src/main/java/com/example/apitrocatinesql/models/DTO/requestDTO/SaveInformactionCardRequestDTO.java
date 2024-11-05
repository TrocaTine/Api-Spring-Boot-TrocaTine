package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Data Transfer Object for saving credit card information")
public record SaveInformactionCardRequestDTO(

        @NotNull
        @Schema(description = "Email of the user associated with the card", example = "user@example.com")
        String email,

        @NotNull
        @Schema(description = "Credit card number", example = "1234 5678 9012 3456")
        String cardNumber,

        @NotNull
        @Schema(description = "Expiration date of the credit card", example = "2025-12-31")
        LocalDate expirationDate,

        @NotNull
        @Schema(description = "CVV of the credit card", example = "123")
        int cvv
) {
}
