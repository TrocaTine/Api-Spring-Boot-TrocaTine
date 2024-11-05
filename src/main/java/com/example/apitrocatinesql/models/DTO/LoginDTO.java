package com.example.apitrocatinesql.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object representing user login information")
public record LoginDTO(
        @NotNull
        @Schema(description = "User email", example = "user@example.com")
        String email,

        @NotNull
        @Schema(description = "User password", example = "securePassword123")
        String password
) {
}
