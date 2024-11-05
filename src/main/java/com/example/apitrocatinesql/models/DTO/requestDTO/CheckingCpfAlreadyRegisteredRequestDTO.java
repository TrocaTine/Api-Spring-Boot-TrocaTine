package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for checking if a CPF is already registered")
public record CheckingCpfAlreadyRegisteredRequestDTO(

        @NotNull
        @Schema(description = "User's CPF (Brazilian Individual Taxpayer Registry)", example = "123.456.789-09")
        String cpf
) {
}
