package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.*;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.UserService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "User Controller", description = "Controller responsible for managing user information and actions")
public class UserController {

    private final UserService usersService;

    @Operation(summary = "Encrypt user password", description = "Encrypts the provided password for secure storage.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully encrypted password"),
            @ApiResponse(responseCode = "400", description = "Invalid password format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/encrypt-password")
    public StandardResponseDTO encrypt(@Valid @RequestBody EncryptPasswordRequestDTO password){
        EncryptPasswordResponseDTO encryptResponseDTO = usersService.encryptPassword(password.password());
        return new StandardResponseDTO(false, encryptResponseDTO);
    }

    @Operation(summary = "Check if email is already registered", description = "Checks if the given email is already registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email registration status retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid email format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/checking-email-already-registered")
    public StandardResponseDTO checkingEmailAlreadyRegistered(@Valid @RequestBody CheckingEmailAlreadyRegisteredRequestDTO email){
        CheckingEmailAlreadyRegisteredResponseDTO alreadyRegisteredResponseDTO = usersService.checkingEmailAlreadyRegistered(email.email());
        return new StandardResponseDTO(false, alreadyRegisteredResponseDTO);
    }

    @Operation(summary = "Check if CPF is already registered", description = "Checks if the given CPF is already registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CPF registration status retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid CPF format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/checking-cpf-already-registered")
    public StandardResponseDTO checkingCpfAlreadyRegistered(@Valid @RequestBody CheckingCpfAlreadyRegisteredRequestDTO cpf){
        CheckingCpfAlreadyRegisteredResponseDTO alreadyRegisteredResponseDTO = usersService.checkingCpfAlreadyRegistered(cpf.cpf());
        return new StandardResponseDTO(false, alreadyRegisteredResponseDTO);
    }

    @Operation(summary = "Find user personal information", description = "Retrieves personal information for the specified user by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal information retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-personal-information/{email}")
    public StandardResponseDTO findPersonalInformation(@Valid @PathVariable String email){
        FindPersonalInformationResponseDTO findPersonalInformationResponseDTO = usersService.findPersonalInformation(email);
        return new StandardResponseDTO(false, findPersonalInformationResponseDTO);
    }

    @Operation(summary = "Edit user personal information", description = "Updates the personal information for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal information updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/edit-personal-information")
    public StandardResponseDTO editPersonalInformation(@Valid @RequestBody EditPersonalInformationRequestDTO editPersonalInformationRequestDTO){
        EditPersonalInformationResponseDTO editPersonalInformationResponseDTO = usersService.editPersonalInformation(editPersonalInformationRequestDTO);
        return new StandardResponseDTO(false, editPersonalInformationResponseDTO);
    }

    @Operation(summary = "Create a new user", description = "Registers a new user in the system with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user information"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create-user")
    public StandardResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO){
        CreateUserResponseDTO createUserResponseDTO = usersService.createUser(createUserRequestDTO);
        return new StandardResponseDTO(false, createUserResponseDTO);
    }

    @Operation(summary = "Get user ID by email", description = "Retrieves the user ID for the specified email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User ID retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/id-user/{email}")
    public StandardResponseDTO idUser(@PathVariable String email){
        Long idUser = usersService.idUser(email);
        return new StandardResponseDTO(false, idUser);
    }

    @Operation(summary = "Save product information for a user", description = "Saves specific product information associated with the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product information saved successfully"),
            @ApiResponse(responseCode = "404", description = "User or product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/user-info-product/{idProduct}/{email}")
    private StandardResponseDTO saveInfoProduct(@PathVariable Long idProduct, @PathVariable String email){
        SaveInfoProductResponseDTO result = usersService.saveInfoProduct(idProduct, email);
        return new StandardResponseDTO(false, result);
    }
}
