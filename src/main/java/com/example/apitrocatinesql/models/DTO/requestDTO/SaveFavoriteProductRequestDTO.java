package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for saving a favorite product for a user")
public record SaveFavoriteProductRequestDTO(

        @NotNull
        @Schema(description = "Email of the user who is saving the favorite product", example = "user@example.com")
        String email,

        @NotNull
        @Schema(description = "ID of the product being saved as a favorite", example = "12345")
        Long id
) {
}
