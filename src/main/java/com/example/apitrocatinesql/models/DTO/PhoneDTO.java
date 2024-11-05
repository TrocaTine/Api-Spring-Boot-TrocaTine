package com.example.apitrocatinesql.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a phone number")
public record PhoneDTO(
        @Schema(description = "Phone number", example = "+5511999998888")
        String number
) {
}
