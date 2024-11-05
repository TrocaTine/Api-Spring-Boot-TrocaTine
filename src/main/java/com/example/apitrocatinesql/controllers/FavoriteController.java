package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.SaveFavoriteProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.UnfavoriteProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@AllArgsConstructor
@Tag(name = "Favorite Controller", description = "Controller responsible for managing product favorites")
public class FavoriteController {

    private FavoriteService favoriteService;


    @Operation(summary = "Find favorite products by user email", description = "Returns a list of all favorite products associated with the user's email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of favorite products"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/find-product-favorite/{email}")
    public StandardResponseDTO findProductFavorite(@Valid @PathVariable String email) {
        List<FindProductFavoriteResponseDTO> productFavorite = favoriteService.findProductFavorite(email);
        return new StandardResponseDTO(false, productFavorite);
    }

    @Operation(summary = "Save product as favorite", description = "Marks a product as favorite for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marked product as favorite"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/save-favorite-product")
    public StandardResponseDTO saveFavoriteProduct(@Valid @RequestBody SaveFavoriteProductRequestDTO request) {
        SaveFavoriteProductResponseDTO saveFavoriteProduct = favoriteService.savefavoriteProduct(request.email(), request.id());
        return new StandardResponseDTO(false, saveFavoriteProduct);
    }

    @Operation(summary = "Unfavorite a product", description = "Removes a product from the user's list of favorites.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed product from favorites"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/unfavorite-product")
    public StandardResponseDTO unfavoriteProduct(@Valid @RequestBody UnfavoriteProductRequestDTO request) {
        UnfavoriteProductResponseDTO unfavoriteProduct = favoriteService.unfavoriteProduct(request.id(), request.email());
        return new StandardResponseDTO(false, unfavoriteProduct);
    }
}
