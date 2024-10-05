package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserRequestDTO(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String cpf,
        @NotNull LocalDate birthDate,
        @NotNull Boolean admin,
        @NotNull String nickname,
        @NotNull String password,
        @NotNull String street,
        @NotNull Integer number,
        @NotNull String city,
        @NotNull String state,
        @NotNull String neighborhood,
        @NotNull String complement,
        @NotNull String cep,
        @NotNull String numberPhone
) {
}
