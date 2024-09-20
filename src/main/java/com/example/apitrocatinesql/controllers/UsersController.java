package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.EncryptPasswordRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.EncryptPasswordResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/encrypt-password")
    public StandardResponseDTO encrypt(@Valid @RequestBody EncryptPasswordRequestDTO password){
        EncryptPasswordResponseDTO encryptResponseDTO = usersService.encryptPassword(password.password());
        return new StandardResponseDTO(false, encryptResponseDTO);
    }



}
