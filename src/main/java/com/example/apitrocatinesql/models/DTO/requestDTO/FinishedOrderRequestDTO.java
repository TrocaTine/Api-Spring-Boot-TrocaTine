package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FinishedOrderRequestDTO(

        @NotNull
        String email,
        @NotNull
        Long idProduct,
        @NotNull
        String numberCard,
        @NotNull
        String paymentType,
        @NotNull
        BigDecimal value
) {
}
