package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Favorite;
import com.example.apitrocatinesql.models.HistoryShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryShoppingCartRepository extends JpaRepository<HistoryShoppingCart, Long> {
}
