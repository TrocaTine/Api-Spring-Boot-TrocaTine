package com.example.apitrocatinesql.models.DTO.requestDTO;

import com.example.apitrocatinesql.models.DTO.PhoneDTO;
import com.example.apitrocatinesql.models.Phone;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EditPersonalInformationRequestDTO(
        @NotNull
        String email,
        @NotNull
        String newEmail,
        @NotNull
        String number,
        @NotNull
        String nickname,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String cpf,
        @NotNull
        LocalDate birthDate


) {
}
