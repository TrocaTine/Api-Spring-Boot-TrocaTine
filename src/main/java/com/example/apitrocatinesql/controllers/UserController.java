package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.*;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService usersService;

    @GetMapping("/encrypt-password")
    public StandardResponseDTO encrypt(@Valid @RequestBody EncryptPasswordRequestDTO password){
        EncryptPasswordResponseDTO encryptResponseDTO = usersService.encryptPassword(password.password());
        return new StandardResponseDTO(false, encryptResponseDTO);
    }

    @GetMapping("/checking-email-already-registered")
    public StandardResponseDTO checkingEmailAlreadyRegistered(@Valid @RequestBody CheckingEmailAlreadyRegisteredRequestDTO email){
        CheckingEmailAlreadyRegisteredResponseDTO alreadyRegisteredResponseDTO = usersService.checkingEmailAlreadyRegistered(email.email());
        return new StandardResponseDTO(false, alreadyRegisteredResponseDTO);

    }

    @GetMapping("/checking-cpf-already-registered")
    public StandardResponseDTO checkingCpfAlreadyRegistered(@Valid @RequestBody CheckingCpfAlreadyRegisteredRequestDTO cpf){
        CheckingCpfAlreadyRegisteredResponseDTO alreadyRegisteredResponseDTO = usersService.checkingCpfAlreadyRegistered(cpf.cpf());
        return new StandardResponseDTO(false, alreadyRegisteredResponseDTO);
    }

    @GetMapping("/find-personal-information")
    public StandardResponseDTO findPersonalInformation(@Valid @RequestBody FindPersonalInformationRequestDTO email){
        FindPersonalInformationResponseDTO findPersonalInformationResponseDTO = usersService.findPersonalInformation(email.email());
        return new StandardResponseDTO(false, findPersonalInformationResponseDTO);
    }

    @PostMapping("/edit-personal-information")
    public StandardResponseDTO editPersonalInformation(@Valid @RequestBody EditPersonalInformationRequestDTO editPersonalInformationRequestDTO){
        EditPersonalInformationResponseDTO editPersonalInformationResponseDTO = usersService.editPersonalInformation(editPersonalInformationRequestDTO);
        return new StandardResponseDTO(false, editPersonalInformationResponseDTO);
    }

    @PostMapping("/create-user")
    public StandardResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO){
        CreateUserResponseDTO createUserResponseDTO = usersService.createUser(createUserRequestDTO);
        return new StandardResponseDTO(false, createUserResponseDTO);
    }





}
