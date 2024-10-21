package com.example.apitrocatinesql.models.DTO.requestDTO;

import java.time.LocalDate;

public record SaveInformactionCardRequestDTO(
        String email,
        String cardNumber,
        LocalDate expirationDate,
        int cvv
) {
}
