package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Tag(name = "Category Controller", description = "Controller responsible for managing product categories")
public class CategoryController {

    private CategoryService categoryService;

    @Operation(summary = "Find all categories", description = "Returns a list of all available product categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of categories", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @GetMapping("/find-all-category")
    public StandardResponseDTO findAllCategory() {
        List<String> result = categoryService.findAllCategory();
        return new StandardResponseDTO(false, result);
    }
}
