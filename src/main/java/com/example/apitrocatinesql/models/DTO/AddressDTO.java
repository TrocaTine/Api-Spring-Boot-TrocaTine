package com.example.apitrocatinesql.models.DTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing an address")
public record AddressDTO(
        @Schema(description = "Street name", example = "Rua Irineu José Bordon")
        String street,

        @Schema(description = "House number", example = "456")
        Integer number,

        @Schema(description = "City name", example = "Osasco")
        String city,

        @Schema(description = "State name", example = "SP")
        String state,

        @Schema(description = "Additional address information", example = "Apt 93C")
        String complement,

        @Schema(description = "CEP address", example = "12345678")
        String cep
) {
}
