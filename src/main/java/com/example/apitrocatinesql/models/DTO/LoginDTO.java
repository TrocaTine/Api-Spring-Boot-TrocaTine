package com.example.apitrocatinesql.models.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;

public record LoginDTO(
        @NotNull
        String email,
        @NotNull
        String password) {


}

