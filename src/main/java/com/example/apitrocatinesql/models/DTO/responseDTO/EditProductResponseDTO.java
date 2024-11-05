package com.example.apitrocatinesql.models.DTO.responseDTO;

import com.example.apitrocatinesql.models.DTO.TagDTO;

import java.math.BigDecimal;
import java.util.List;

public record EditProductResponseDTO(
        String emailUser,
        String name,
        String description,
        BigDecimal value,
        Long stock,
        Boolean flagTrade,
        List<String> tags,
        List<String> categories
) {
}
