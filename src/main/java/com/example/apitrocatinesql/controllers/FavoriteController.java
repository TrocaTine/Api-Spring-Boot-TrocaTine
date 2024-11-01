package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.SaveFavoriteProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.UnfavoriteProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@AllArgsConstructor
public class FavoriteController {

    private FavoriteService favoriteService;

    @GetMapping("/find-product-favorite/{email}")
    public StandardResponseDTO findProductFavorite(@Valid @PathVariable String email){
        List<FindProductFavoriteResponseDTO> productFavorite = favoriteService.findProductFavorite(email);
        return new StandardResponseDTO(false, productFavorite);
    }

    @PostMapping("/save-favorite-product")
    public StandardResponseDTO saveFavoriteProduct(@Valid @RequestBody SaveFavoriteProductRequestDTO request){
        SaveFavoriteProductResponseDTO savefavoriteProduct = favoriteService.savefavoriteProduct(request.email(), request.id());
        return new StandardResponseDTO(false, savefavoriteProduct);
    }

    @PostMapping("/unfavorite-product")
    public StandardResponseDTO unfavoriteProduct(@Valid @RequestBody UnfavoriteProductRequestDTO request){
        UnfavoriteProductResponseDTO unfavoriteProduct = favoriteService.unfavoriteProduct(request.id(), request.email());
        return new StandardResponseDTO(false, unfavoriteProduct);
    }


}
