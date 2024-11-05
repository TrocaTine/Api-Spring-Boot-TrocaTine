package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.models.Category;
import com.example.apitrocatinesql.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;


    public List<String> findAllCategory(){
        List<Category> listCategory = categoryRepository.findAll();
        return listCategory.stream().map(Category::getName).collect(Collectors.toList());
    }
}
