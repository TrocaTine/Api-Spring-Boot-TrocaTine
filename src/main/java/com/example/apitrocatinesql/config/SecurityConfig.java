package com.example.apitrocatinesql.config;

import com.example.apitrocatinesql.filter.JwtAuthenticationFilter;
import com.example.apitrocatinesql.models.DTO.ExceptionHandlerDTO;
import com.example.apitrocatinesql.models.DTO.StandarResponseDTO;
import com.example.apitrocatinesql.services.CustomUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailService userDetailsService;

    public SecurityConfig(CustomUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtAuthenticationFilter(userDetailsService, secretKey()),
                        UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            StandarResponseDTO standarResponseDTO = new StandarResponseDTO(true, new ExceptionHandlerDTO(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage(), request.getRequestURI()));
                            ObjectMapper mapper = new ObjectMapper();
                            String jsonResponse = mapper.writeValueAsString(standarResponseDTO);
                            response.getWriter().write(jsonResponse);

                        })
                        .accessDeniedHandler(((request, response, accessDeniedException) -> {
                                    response.setContentType("application/json");
                                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                    StandarResponseDTO standarResponseDTO = new StandarResponseDTO(true, new ExceptionHandlerDTO(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage(), request.getRequestURI()));
                                    ObjectMapper mapper = new ObjectMapper();
                                    String jsonResponse = mapper.writeValueAsString(standarResponseDTO);
                                    response.getWriter().write(jsonResponse);
                                })
                        ));

        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecretKey secretKey(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

}
