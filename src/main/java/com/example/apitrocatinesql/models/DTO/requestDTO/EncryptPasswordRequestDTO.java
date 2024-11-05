package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for requesting password encryption")
public record EncryptPasswordRequestDTO(
        @NotNull(message = "Password cannot be null.")
        @Schema(description = "The password to be encrypted", example = "mySecurePassword123")
        String password) {
}
