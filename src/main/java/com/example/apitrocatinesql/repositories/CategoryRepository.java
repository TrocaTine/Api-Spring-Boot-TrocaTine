package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findCategoryByNameIgnoreCase(String name);

}
