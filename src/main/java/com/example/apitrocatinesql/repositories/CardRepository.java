package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.SavedCard;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<SavedCard, Long> {

    List<SavedCard> findSavedCardByUser(User user);
}
