package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

public record DeleteShoppingCartsRequestDTO(

        @NotNull
        String email,
        @NotNull
        Long idProduct
) {
}
