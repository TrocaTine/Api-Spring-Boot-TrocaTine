package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.FindCardUserRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SaveInformactionCardRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindCardUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveInformactionCardResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.CardService;
import com.example.apitrocatinesql.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/find-all-category")
    public StandardResponseDTO findAllCategory(){
        List<String> result = categoryService.findAllCategory();
        return new StandardResponseDTO(false, result);
    }
}
