package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

}
