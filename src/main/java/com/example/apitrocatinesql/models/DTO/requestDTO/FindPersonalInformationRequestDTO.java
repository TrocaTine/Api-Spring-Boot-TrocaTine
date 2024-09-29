package com.example.apitrocatinesql.models.DTO.requestDTO;

import jakarta.validation.constraints.NotNull;

public record FindPersonalInformationRequestDTO(
        @NotNull
        String email){
}
