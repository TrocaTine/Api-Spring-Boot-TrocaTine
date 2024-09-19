package com.example.apitrocatinesql.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record LoginDTO(
        String email,
        String password) {


}

