package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.FindTagByTypeRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.CategoryService;
import com.example.apitrocatinesql.services.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    @GetMapping("/find-tag-type")
    public StandardResponseDTO findTagByType(@RequestBody @Valid FindTagByTypeRequestDTO requestDTO){
        List<String> result = tagService.findTagByType(requestDTO.type());
        return new StandardResponseDTO(false, result);
    }
}
