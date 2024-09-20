package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.models.DTO.responseDTO.EncryptPasswordResponseDTO;
import com.example.apitrocatinesql.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public EncryptPasswordResponseDTO encryptPassword(String password){
        String encryptedPassword = passwordEncoder.encode(password);
        return new EncryptPasswordResponseDTO(encryptedPassword);
    }


}
