package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.config.SecurityConfig;
import com.example.apitrocatinesql.exception.ExceptionHandlerDTO;
import com.example.apitrocatinesql.models.DTO.LoginDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.TokenResponseDTO;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.UserRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;

@RestController
public class AuthController {

    //    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);
    public SecretKey secretKey;
    private UserRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private SecurityConfig securityConfig;

    @Autowired
    public AuthController(UserRepository usersRepository, PasswordEncoder passwordEncoder, SecretKey secretKey, SecurityConfig securityConfig) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretKey  = secretKey;
        this.securityConfig = securityConfig;
    }
    @PostMapping("/api/auth/login")
    public StandardResponseDTO login(@Valid @RequestBody LoginDTO loginRequest, HttpServletRequest request) throws JsonProcessingException {
        User user = usersRepository.findUserByEmail(loginRequest.email());
        if (user != null && passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            try {
                String token = Jwts.builder()
                        .setSubject(loginRequest.email())
                        .claim("auth", Collections.emptyList())
                        .setExpiration(new Date(System.currentTimeMillis() + 86_400_000)) // 1 dia de validade
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact();
                return new StandardResponseDTO(false, new TokenResponseDTO("Bearer " + token));
            } catch (Exception e) {
                StandardResponseDTO standardResponseDTO = new StandardResponseDTO(
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
            StandardResponseDTO standardResponseDTO = new StandardResponseDTO(
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
