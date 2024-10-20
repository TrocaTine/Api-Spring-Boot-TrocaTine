package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Favorite;
import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findFavoriteByUser(User user);
    Favorite findFavoriteByProductAndUser(Product product, User user);


}
