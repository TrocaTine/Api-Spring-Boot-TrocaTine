package com.example.apitrocatinesql.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a tag associated with a product")
public record TagDTO(
        @Schema(description = "Name of the tag", example = "P")
        String name,

        @Schema(description = "Type of the tag", example = "Clothe size")
        String type
) {
}
