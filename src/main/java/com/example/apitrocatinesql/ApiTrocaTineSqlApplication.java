package com.example.apitrocatinesql;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(
        title = "Api TrocaTine",
        description = "API is a CRUD with the tables necessary for the application TrocaTine to function",
        version = "1"))
@SpringBootApplication
public class ApiTrocaTineSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTrocaTineSqlApplication.class, args);
    }

}
