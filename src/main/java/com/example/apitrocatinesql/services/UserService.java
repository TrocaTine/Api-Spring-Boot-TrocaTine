package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.exception.UserAlreadyExistsException;
import com.example.apitrocatinesql.models.DTO.AddressDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.CreateUserRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.EditPersonalInformationRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.models.Phone;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public EncryptPasswordResponseDTO encryptPassword(String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        return new EncryptPasswordResponseDTO(encryptedPassword);
    }

    public CheckingEmailAlreadyRegisteredResponseDTO checkingEmailAlreadyRegistered(String email) {
        boolean emailRegistered = usersRepository.existsUserByEmail(email);
        return new CheckingEmailAlreadyRegisteredResponseDTO(emailRegistered);
    }

    public CheckingCpfAlreadyRegisteredResponseDTO checkingCpfAlreadyRegistered(String cpf) {
        boolean cpfRegistered = usersRepository.existsUserByCpf(cpf);
        return new CheckingCpfAlreadyRegisteredResponseDTO(cpfRegistered);
    }

    public FindPersonalInformationResponseDTO findPersonalInformation(String email) {
        User personalInf = usersRepository.findUserByEmail(email);
        FindPersonalInformationResponseDTO responseDTO = null;
        if (personalInf != null) {
            Set<String> phones = personalInf.getPhones().stream()
                    .map(phone -> phone.getNumber())
                    .collect(Collectors.toSet());
            Set<AddressDTO> addresses = personalInf.getAddresses().stream()
                    .map(address -> new AddressDTO(
                            address.getStreet(),
                            address.getNumber(),
                            address.getCity(),
                            address.getState(),
                            address.getComplement(),
                            address.getCep()))
                    .collect(Collectors.toSet());
            responseDTO = new FindPersonalInformationResponseDTO(
                    phones, personalInf.getCpf(), personalInf.getBirthDate(), addresses,
                    personalInf.getFirstName() + " " + personalInf.getLastName(), personalInf.getNickname(), personalInf.getEmail());
        } else {
            throw new NotFoundUser("Not found user");
        }
        return responseDTO;
    }

    public EditPersonalInformationResponseDTO editPersonalInformation(EditPersonalInformationRequestDTO editPersonalInformationRequestDTO) {
        User userFind = usersRepository.findUserByEmail(editPersonalInformationRequestDTO.email());
        if (userFind != null) {
            userFind.setEmail(editPersonalInformationRequestDTO.newEmail());

            Set<Phone> phone = userFind.getPhones();
            phone.add(new Phone(editPersonalInformationRequestDTO.number(), userFind));
            userFind.setPhones(phone);

            userFind.setNickname(editPersonalInformationRequestDTO.nickname());

            userFind.setFirstName(editPersonalInformationRequestDTO.firstName());

            userFind.setLastName(editPersonalInformationRequestDTO.lastName());

            userFind.setBirthDate(editPersonalInformationRequestDTO.birthDate());

            User userSaved = usersRepository.save(userFind);
            if (userSaved == userFind) {
                return new EditPersonalInformationResponseDTO(true);
            } else {
                return new EditPersonalInformationResponseDTO(false);
            }
        } else {
            throw new NotFoundUser("Not Found User");
        }
    }

    public CreateUserResponseDTO createUser(CreateUserRequestDTO request){
        try {
            EncryptPasswordResponseDTO encryptPassword  = encryptPassword(request.password());
            usersRepository.createUser(request.firstName(), request.lastName(), request.email(), request.cpf(), request.birthDate(),
                    request.admin(),request.nickname(), encryptPassword.password(), request.street(), request.number(), request.city(), request.state(), request.neighborhood(), request.complement(), request.cep(), request.numberPhone());

        } catch (Exception ex) {
            if (ex.getMessage().contains("Usuario já existente")) {
                throw new UserAlreadyExistsException("User already registered");
            }
            throw new RuntimeException("Error creating user : " + ex.getMessage());
        }
        return new CreateUserResponseDTO(true);
    }


}
