package com.example.apitrocatinesql.models.DTO.responseDTO;

public record FindUserAddressResponseDTO(
        String street,
        Integer number,
        String city,
        String state,
        String complement,
        String cep
) {

}
