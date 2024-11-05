package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.DTO.requestDTO.CreateUserRequestDTO;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByCpf(String cpf);

    User findUserByIdUser(Long idUser);

    @Procedure(procedureName = "create_user")
    void createUser(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("email") String email,
            @Param("cpf") String cpf,
            @Param("birth_date") LocalDate birthDate,
            @Param("admin") Boolean admin,
            @Param("nickname") String nickname,
            @Param("password") String password,
            @Param("street") String street,
            @Param("number") Integer number,
            @Param("city") String city,
            @Param("state") String state,
            @Param("neighborhood") String district,
            @Param("complement") String complement,
            @Param("cep") String postalCode,
            @Param("phone") String phone
    );
}
