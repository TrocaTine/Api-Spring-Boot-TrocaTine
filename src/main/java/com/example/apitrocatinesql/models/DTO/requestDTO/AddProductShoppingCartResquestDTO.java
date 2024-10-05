package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

public record AddProductShoppingCartResquestDTO(
        @NotNull
        long idProduct,
        @NotNull
        int quantity,
        @NotNull
        String email


) {
}
