package com.example.apitrocatinesql.models.DTO;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.Column;
public record AddressDTO(
        String street,
        Integer number,
        String city,
        String state,
        String complement,
        String cep

    ) {
}
