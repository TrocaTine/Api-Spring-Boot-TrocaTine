package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.FindProductCardTagRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.FindProductFavoriteRequestDTO;
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

    @PostMapping("/find-product-favorite")
    public StandardResponseDTO findProductFavorite(@Valid @RequestBody FindProductFavoriteRequestDTO request){
        List<FindProductFavoriteResponseDTO> productFavorite = favoriteService.findProductFavorite(request.email());
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
