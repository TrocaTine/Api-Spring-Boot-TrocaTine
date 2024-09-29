package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByCpf(String cpf);


}
