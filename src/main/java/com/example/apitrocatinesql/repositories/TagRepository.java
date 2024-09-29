package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Tag findTagByNameIgnoreCase(String name);


}
