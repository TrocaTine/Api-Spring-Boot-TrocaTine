package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    @GetMapping("/find-tag-type/{type}")
    public StandardResponseDTO findTagByType(@PathVariable @Valid String type){
        List<String> result = tagService.findTagByType(type);
        return new StandardResponseDTO(false, result);
    }
}
