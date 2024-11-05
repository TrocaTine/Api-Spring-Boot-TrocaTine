package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for checking if an email is already registered")
public record CheckingEmailAlreadyRegisteredRequestDTO(
        @NotNull(message = "Email cannot be null.")
        @Schema(description = "User's email address to check for registration", example = "john.doe@example.com")
        String email
) {
}
