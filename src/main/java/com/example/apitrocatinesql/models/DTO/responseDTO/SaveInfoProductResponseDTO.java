package com.example.apitrocatinesql.models.DTO.responseDTO;

public record SaveInfoProductResponseDTO(
        Long idUserProduct,
        String nicknameProduct,
        String emailProduct,
        Long idUser,
        String nicknameUser,
        String emailUser
){
}
