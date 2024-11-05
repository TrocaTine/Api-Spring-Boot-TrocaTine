package com.example.apitrocatinesql.models.DTO.requestDTO;

import com.example.apitrocatinesql.models.DTO.AddressDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for saving a user's address")
public record SaveAddressRequestDTO(

        @NotNull
        @Schema(description = "Email of the user for whom the address is being saved", example = "user@example.com")
        String email,

        @NotNull
        @Schema(description = "Address details", implementation = AddressDTO.class)
        AddressDTO address
) {
}
