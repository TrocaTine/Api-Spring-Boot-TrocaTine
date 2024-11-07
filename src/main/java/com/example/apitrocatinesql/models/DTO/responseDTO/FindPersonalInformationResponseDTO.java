package com.example.apitrocatinesql.models.DTO.responseDTO;
import java.time.LocalDate;
import java.util.Set;

import com.example.apitrocatinesql.models.DTO.AddressDTO;

public record FindPersonalInformationResponseDTO(
        Set<String> phone,
        String cpf,
        LocalDate birthDate,
        Set<AddressDTO> addresses,
        String fullName,
        String nickname,
        String email,
        String senha

) {
}
