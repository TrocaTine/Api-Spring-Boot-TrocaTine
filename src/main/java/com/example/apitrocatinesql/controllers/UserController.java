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

    @PostMapping("/encrypt-password")
    public StandardResponseDTO encrypt(@Valid @RequestBody EncryptPasswordRequestDTO password){
        EncryptPasswordResponseDTO encryptResponseDTO = usersService.encryptPassword(password.password());
        return new StandardResponseDTO(false, encryptResponseDTO);
    }

    @PostMapping("/checking-email-already-registered")
    public StandardResponseDTO checkingEmailAlreadyRegistered(@Valid @RequestBody CheckingEmailAlreadyRegisteredRequestDTO email){
        CheckingEmailAlreadyRegisteredResponseDTO alreadyRegisteredResponseDTO = usersService.checkingEmailAlreadyRegistered(email.email());
        return new StandardResponseDTO(false, alreadyRegisteredResponseDTO);

    }

    @PostMapping("/checking-cpf-already-registered")
    public StandardResponseDTO checkingCpfAlreadyRegistered(@Valid @RequestBody CheckingCpfAlreadyRegisteredRequestDTO cpf){
        CheckingCpfAlreadyRegisteredResponseDTO alreadyRegisteredResponseDTO = usersService.checkingCpfAlreadyRegistered(cpf.cpf());
        return new StandardResponseDTO(false, alreadyRegisteredResponseDTO);
    }

    @GetMapping("/find-personal-information/{email}")
    public StandardResponseDTO findPersonalInformation(@Valid @PathVariable String  email){
        FindPersonalInformationResponseDTO findPersonalInformationResponseDTO = usersService.findPersonalInformation(email);
        return new StandardResponseDTO(false, findPersonalInformationResponseDTO);
    }

    @PutMapping("/edit-personal-information")
    public StandardResponseDTO editPersonalInformation(@Valid @RequestBody EditPersonalInformationRequestDTO editPersonalInformationRequestDTO){
        EditPersonalInformationResponseDTO editPersonalInformationResponseDTO = usersService.editPersonalInformation(editPersonalInformationRequestDTO);
        return new StandardResponseDTO(false, editPersonalInformationResponseDTO);
    }

    @PostMapping("/create-user")
    public StandardResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO){
        CreateUserResponseDTO createUserResponseDTO = usersService.createUser(createUserRequestDTO);
        return new StandardResponseDTO(false, createUserResponseDTO);
    }

    @GetMapping("/id-user/{email}")
    public StandardResponseDTO idUser(@PathVariable String email){
        Long idUser = usersService.idUser(email);
        return new StandardResponseDTO(false, idUser);

    }

    @PostMapping("/user-info-product/{idProduct}")
    private StandardResponseDTO saveInfoProduct(@PathVariable Long idProduct){
        SaveInfoProductResposeDTO result = usersService.saveInfoProduct(idProduct);
        return new StandardResponseDTO(false, result);
    }






}
