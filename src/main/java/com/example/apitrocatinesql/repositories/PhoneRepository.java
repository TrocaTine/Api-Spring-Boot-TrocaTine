package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Phone;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Modifying
    @Query("DELETE FROM Phone e WHERE e.user.idUser = ?1")
    void deleteById(Long id);


}
