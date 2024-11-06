package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.SaveFavoriteProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.UnfavoriteProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductFavoriteResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveFavoriteProductResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.UnfavoriteProductResponseDTO;
import com.example.apitrocatinesql.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(responseCode = "200", description = "Successfully returned list of favorite products", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FindProductFavoriteResponseDTO.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @GetMapping("/find-product-favorite/{email}")
    public StandardResponseDTO findProductFavorite(@Valid @PathVariable String email) {
        List<FindProductFavoriteResponseDTO> productFavorite = favoriteService.findProductFavorite(email);
        return new StandardResponseDTO(false, productFavorite);
    }

    @Operation(summary = "Save product as favorite", description = "Marks a product as favorite for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marked product as favorite", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SaveFavoriteProductResponseDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @PostMapping("/save-favorite-product")
    public StandardResponseDTO saveFavoriteProduct(@Valid @RequestBody SaveFavoriteProductRequestDTO request) {
        SaveFavoriteProductResponseDTO saveFavoriteProduct = favoriteService.savefavoriteProduct(request.email(), request.id());
        return new StandardResponseDTO(false, saveFavoriteProduct);
    }

    @Operation(summary = "Unfavorite a product", description = "Removes a product from the user's list of favorites.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed product from favorites", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UnfavoriteProductResponseDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @PostMapping("/unfavorite-product")
    public StandardResponseDTO unfavoriteProduct(@Valid @RequestBody UnfavoriteProductRequestDTO request) {
        UnfavoriteProductResponseDTO unfavoriteProduct = favoriteService.unfavoriteProduct(request.id(), request.email());
        return new StandardResponseDTO(false, unfavoriteProduct);
    }
}
