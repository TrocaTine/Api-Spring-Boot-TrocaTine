package com.example.apitrocatinesql.models.DTO.responseDTO;

import java.math.BigDecimal;

public record FindProductShoppingCartResponseDTO(

        String title,
        BigDecimal value,
        int quality
) {
}
