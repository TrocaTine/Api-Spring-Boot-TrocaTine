package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Category;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductCardTradeResponseDTO;
import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.Tag;

import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByFlagTradeIsTrue();
    List<Product> findProductByFlagTradeIsFalse();
    List<Product> findProductByTags(Tag tag);
    List<Product> findProductByCategories(Category category);
    List<Product> findProductByNameIsContainingIgnoreCase(String name);

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN p.tags t " +
            "WHERE t.name IN (:tags)")
    List<Product> findProductByListTag(List<String> tags);

    Product findProductByIdProduct(Long id);

    List<Product> findProductByUser(User user);

    @Modifying
    @Query("DELETE FROM Product e WHERE e.idProduct = ?1")
    void deleteById(Long id);

}
