package com.example.apitrocatinesql.models.DTO;

import jakarta.validation.constraints.NotNull;

public record PhoneDTO (
        @NotNull
        String number
) {

}
