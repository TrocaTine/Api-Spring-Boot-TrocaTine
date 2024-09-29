package com.example.apitrocatinesql.models.DTO.requestDTO;

import com.example.apitrocatinesql.models.DTO.TagDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record EditProductRequestDTO(
        @NotNull
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        BigDecimal value,
        @NotNull
        Long stock,
        @NotNull
        Boolean flagTrade,
        @NotNull
        List<TagDTO> tags,
        @NotNull
        List<String> categories
) {
}
