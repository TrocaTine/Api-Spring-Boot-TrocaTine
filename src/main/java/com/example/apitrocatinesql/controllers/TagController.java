package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.TagService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
@Tag(name = "Tag Controller", description = "Controller responsible for managing product tags")
public class TagController {

    private final TagService tagService;

    @Operation(summary = "Find tags by type", description = "Retrieves a list of tags based on the specified type.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tags"),
            @ApiResponse(responseCode = "404", description = "No tags found for the specified type"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-tag-type/{type}")
    public StandardResponseDTO findTagByType(@Valid @PathVariable String type) {
        List<String> result = tagService.findTagByType(type);
        return new StandardResponseDTO(false, result);
    }


    @GetMapping("/find-tag-all")
    public StandardResponseDTO findTagAll(@Valid @PathVariable String type){
        List<String> result = tagService.findAllTags();
        return new StandardResponseDTO(false, result);
    }
}
