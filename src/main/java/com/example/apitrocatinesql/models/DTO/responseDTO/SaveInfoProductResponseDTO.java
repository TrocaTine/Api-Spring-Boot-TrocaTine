package com.example.apitrocatinesql.models.DTO.responseDTO;

public record SaveInfoProductResponseDTO(
        Long idUserProduct,
        String nicknameProduct,
        Long idUser,
        String nicknameUser
){
}
