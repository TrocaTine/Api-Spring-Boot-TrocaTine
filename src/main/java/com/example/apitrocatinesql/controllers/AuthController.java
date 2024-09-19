package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.ExceptionHandlerDTO;
import com.example.apitrocatinesql.models.DTO.LoginDTO;
import com.example.apitrocatinesql.models.DTO.StandarResponseDTO;
import com.example.apitrocatinesql.models.DTO.TokenDTO;
import com.example.apitrocatinesql.models.Users;
import com.example.apitrocatinesql.repositories.UsersRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.management.relation.Role;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class AuthController {

    //    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);
    public SecretKey secretKey;
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UsersRepository usersRepository, PasswordEncoder passwordEncoder, SecretKey secretKey) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretKey  = secretKey;
    }
    @PostMapping("/api/auth/login")
    public StandarResponseDTO login(@RequestBody LoginDTO loginRequest, HttpServletRequest request) throws JsonProcessingException {
        Users user = usersRepository.findByEmail(loginRequest.email());
        if (user != null && passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            try {
                String token = Jwts.builder()
                        .setSubject(loginRequest.email())
                        .claim("auth", Collections.emptyList())
                        .setExpiration(new Date(System.currentTimeMillis() + 86_400_000)) // 1 dia de validade
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact();
                return new StandarResponseDTO(false, new TokenDTO(token));
            } catch (Exception e) {
                StandarResponseDTO standardResponseDTO = new StandarResponseDTO(
                        true,
                        new ExceptionHandlerDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Error generating token JWT",
                                request.getRequestURI()
                        )
                );
                return standardResponseDTO;
            }
        } else {
            StandarResponseDTO standardResponseDTO = new StandarResponseDTO(
                    true,
                    new ExceptionHandlerDTO(
                            HttpStatus.UNAUTHORIZED.value(),
                            "Invalid credentials",
                            request.getRequestURI()
                    )
            );
            return standardResponseDTO;
        }
    }
}
