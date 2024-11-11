package com.example.apitrocatinesql.repositories;


import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findTagByNameIgnoreCase(String name);
    List<Tag> findTagByType(String type);

}
