package com.example.apitrocatinesql.models.DTO.responseDTO;

import com.example.apitrocatinesql.models.DTO.HighlightDTO;

import java.time.LocalDate;
import java.util.List;

public record FindProductInformationResponseDTO(

        Long id,
        String userNickname,
        java.math.BigDecimal value,
        LocalDate createdAt,
        String name,
        String description,
        List<String> tags,
        List<HighlightDTO> highlight,
        boolean flagTrade


) {
}
