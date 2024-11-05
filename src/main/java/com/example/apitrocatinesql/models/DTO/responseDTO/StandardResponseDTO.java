package com.example.apitrocatinesql.models.DTO.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard response structure for API requests, indicating success or error")
public record StandardResponseDTO(
        @Schema(description = "Indicates if an error occurred", example = "false")
        boolean error,

        @Schema(description = "Generic object containing additional information about the error, if any; otherwise, it contains the response data.",
                example = "{ 'message': 'Operation successful', 'code': 200 }")
        Object data) {
}
