package com.example.apitrocatinesql.models.DTO.responseDTO;

import com.example.apitrocatinesql.models.DTO.HighlightDTO;
import org.springframework.jmx.export.annotation.ManagedNotifications;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FindProductByUserResponseDTO(
        Long id,
        BigDecimal value,
        LocalDate createdAt,
        String name,
        String description,
        List<String> tags,
        List<HighlightDTO> highlight,
        boolean flagTrade
) {
}
