package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.ShoppingCart;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    List<ShoppingCart> findShoppingCartByUser(User user);

}
