package com.example.apitrocatinesql.models.DTO.responseDTO;

import java.time.LocalDate;

public record FindCardUserResponseDTO (
        String cardNumber,
        LocalDate expirationDate,
        int cvv
) {



}
