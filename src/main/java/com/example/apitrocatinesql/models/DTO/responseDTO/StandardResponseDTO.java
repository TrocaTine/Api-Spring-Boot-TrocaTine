package com.example.apitrocatinesql.models.DTO.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Type of return")
public record StandardResponseDTO(
        @Schema(description = "error")
        boolean error,
        Object data) {

    }
