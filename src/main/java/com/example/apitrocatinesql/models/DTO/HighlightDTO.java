package com.example.apitrocatinesql.models.DTO;

import java.time.LocalDate;

public record HighlightDTO(
        LocalDate expirantionAt,
        boolean highlight
)
{
}
