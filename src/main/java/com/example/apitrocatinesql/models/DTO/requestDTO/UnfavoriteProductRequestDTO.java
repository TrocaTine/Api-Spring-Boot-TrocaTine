package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for unfavoriting a product")
public record UnfavoriteProductRequestDTO(

        @Schema(description = "ID of the product to be unfavorited", example = "12345")
        Long id,

        @Schema(description = "Email of the user who wants to unfavorite the product", example = "user@example.com")
        String email
) {
}
