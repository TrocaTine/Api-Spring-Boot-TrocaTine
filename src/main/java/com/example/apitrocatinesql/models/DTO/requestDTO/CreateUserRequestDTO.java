package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

@Schema(description = "Data Transfer Object for creating a new user")
public record CreateUserRequestDTO(
        @NotNull(message = "First name cannot be null.")
        @Schema(description = "User's first name", example = "John")
        String firstName,

        @NotNull(message = "Last name cannot be null.")
        @Schema(description = "User's last name", example = "Doe")
        String lastName,

        @NotNull(message = "Email cannot be null.")
        @Schema(description = "User's email address", example = "john.doe@example.com")
        String email,

        @NotNull(message = "CPF cannot be null.")
        @Schema(description = "User's CPF (Cadastro de Pessoas Físicas)", example = "12345678901")
        String cpf,

        @NotNull(message = "Birth date cannot be null.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Schema(description = "User's birth date in YYYY-MM-DD format", example = "1990-01-01")
        LocalDate birthDate,

        @NotNull(message = "Admin status cannot be null.")
        @Schema(description = "Indicates if the user is an admin", example = "true")
        Boolean admin,

        @NotNull(message = "Nickname cannot be null.")
        @Schema(description = "User's nickname", example = "johndoe93")
        String nickname,

        @NotNull(message = "Password cannot be null.")
        @Schema(description = "User's password", example = "securePassword123")
        String password,

        @NotNull(message = "Street cannot be null.")
        @Schema(description = "Street name of the user's address", example = "123 Main St")
        String street,

        @NotNull(message = "Number cannot be null.")
        @Schema(description = "House number of the user's address", example = "456")
        Integer number,

        @NotNull(message = "City cannot be null.")
        @Schema(description = "City of the user's address", example = "New York")
        String city,

        @NotNull(message = "State cannot be null.")
        @Schema(description = "State of the user's address", example = "NY")
        String state,

        @NotNull(message = "Neighborhood cannot be null.")
        @Schema(description = "Neighborhood of the user's address", example = "Downtown")
        String neighborhood,

        @NotNull(message = "Complement cannot be null.")
        @Schema(description = "Additional address information", example = "Apt 2B")
        String complement,

        @NotNull(message = "CEP cannot be null.")
        @Schema(description = "Postal code (CEP) of the user's address", example = "10001")
        String cep,

        @NotNull(message = "Phone number cannot be null.")
        @Schema(description = "User's phone number", example = "+1 234 567 890")
        String numberPhone
) {
}
