package com.example.apitrocatinesql.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Data Transfer Object representing a highlight for a product")
public record HighlightDTO(
        @Schema(description = "Expiration date for the highlight", example = "2024-12-31")
        LocalDate expirationAt,

        @Schema(description = "Indicates if the product is highlighted", example = "true")
        boolean highlight
) {
}
