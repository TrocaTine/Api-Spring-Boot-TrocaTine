package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Trocadinha;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrocadinhaRepository extends JpaRepository<Trocadinha, Long> {

    Trocadinha findByUserAndExpirationDateGreaterThanEqual(User user, LocalDate expirationDate);
    List<Trocadinha> findTrocadinhaByExpirationDateGreaterThanEqual(LocalDate expirationDate);
}
