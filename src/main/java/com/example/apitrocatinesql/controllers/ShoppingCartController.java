package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.AddProductShoppingCartResquestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.FindProductShoppingCartResquestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.AddProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
@AllArgsConstructor
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @PostMapping("/add-product")
    public StandardResponseDTO addProductShoppingCart(@Valid @RequestBody AddProductShoppingCartResquestDTO resquest){
        AddProductShoppingCartResponseDTO addProductShoppingCart = shoppingCartService.addProductShoppingCart(resquest);
        return new StandardResponseDTO(false, addProductShoppingCart);
    }

    @GetMapping("find-product")
    public StandardResponseDTO findProductShoppingCart(@Valid @RequestBody FindProductShoppingCartResquestDTO resquest){
        List<FindProductShoppingCartResponseDTO> findProduct = shoppingCartService.findProductShoppingCart(resquest);
        return new StandardResponseDTO(false, findProduct);
    }
}
