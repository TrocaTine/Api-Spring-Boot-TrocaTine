package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Push;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PushRepository extends JpaRepository<Push, Long> {

    List<Push> findPushByUsersAndCreatedAtGreaterThanEqual(User user, LocalDate createdAt);
}
