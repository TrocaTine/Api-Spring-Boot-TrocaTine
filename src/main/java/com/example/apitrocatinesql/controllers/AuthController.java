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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
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
@Tag(name = "Authenticator controller", description = "Controller responsible for managing authentication")
public class AuthController {

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

    @Operation(summary = "Create and find a token by user info", description = "Return the user token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned user token", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlerDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlerDTO.class))
            })
    })
    @PostMapping("/api/auth/login")
    public StandardResponseDTO login(@Valid @RequestBody LoginDTO loginRequest, HttpServletRequest request) {
        User user = usersRepository.findUserByEmail(loginRequest.email());
        if (user != null && passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            try {
                String token = Jwts.builder()
                        .setSubject(loginRequest.email())
                        .claim("auth", Collections.emptyList())
                        .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact();
                return new StandardResponseDTO(false, new TokenResponseDTO("Bearer " + token));
            } catch (Exception e) {
                return new StandardResponseDTO(
                        true,
                        new ExceptionHandlerDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Error generating token JWT",
                                request.getRequestURI()
                        )
                );
            }
        } else {
            return new StandardResponseDTO(
                    true,
                    new ExceptionHandlerDTO(
                            HttpStatus.UNAUTHORIZED.value(),
                            "Invalid credentials",
                            request.getRequestURI()
                    )
            );
        }
    }

    @Operation(summary = "Connect to API", description = "Test API connectivity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully connected to API", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @GetMapping("/api")
    public StandardResponseDTO conect() {
        return new StandardResponseDTO(true, null);
    }
}
