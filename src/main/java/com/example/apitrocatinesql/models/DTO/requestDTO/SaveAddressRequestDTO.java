package com.example.apitrocatinesql.models.DTO.requestDTO;

import com.example.apitrocatinesql.models.DTO.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record SaveAddressRequestDTO(
        @NotNull
        String email,
        @NotNull
        AddressDTO address
) {
}
