package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FindProductCardListTagsRequestDTO(
        @NotNull
        List<String> tagName
) {
}
