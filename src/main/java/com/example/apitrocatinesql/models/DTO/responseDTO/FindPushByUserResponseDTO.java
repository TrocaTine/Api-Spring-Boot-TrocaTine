package com.example.apitrocatinesql.models.DTO.responseDTO;

import java.time.LocalDate;

public record FindPushByUserResponseDTO(
        String title,
        String description,
        LocalDate createdAt
) {
}
