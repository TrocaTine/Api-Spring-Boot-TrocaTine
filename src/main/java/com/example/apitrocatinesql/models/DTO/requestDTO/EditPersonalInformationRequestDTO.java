package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Data Transfer Object for editing personal information of a user")
public record EditPersonalInformationRequestDTO(

        @NotNull
        @Schema(description = "Current email of the user", example = "user@example.com")
        String email,

        @NotNull
        @Schema(description = "New email to be updated", example = "newuser@example.com")
        String newEmail,

        @NotNull
        @Schema(description = "User's phone number", example = "+5511999998888")
        String number,

        @NotNull
        @Schema(description = "User's nickname", example = "CoolUser123")
        String nickname,

        @NotNull
        @Schema(description = "User's first name", example = "John")
        String firstName,

        @NotNull
        @Schema(description = "User's last name", example = "Doe")
        String lastName,

        @NotNull
        @Schema(description = "User's CPF (Brazilian Individual Taxpayer Registry)", example = "123.456.789-09")
        String cpf,

        @NotNull
        @Schema(description = "User's birth date in the format YYYY-MM-DD", example = "1990-01-01")
        LocalDate birthDate
) {
}
