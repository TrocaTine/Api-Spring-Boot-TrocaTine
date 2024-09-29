package com.example.apitrocatinesql.models.DTO.responseDTO;

import com.example.apitrocatinesql.models.DTO.HighlightDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record FindProductCardResponseDTO(
        Long id,
        java.math.BigDecimal value,
        LocalDate createdAt,
        String name,
        String description,
        List<String> tags,
        List<HighlightDTO> highlight,
        boolean flagTrade

) {
}
