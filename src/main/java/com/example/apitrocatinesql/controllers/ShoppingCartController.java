package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.AddProductShoppingCartResquestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.AddProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.DeleteShoppingCartsResponseDTO;
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

    @GetMapping("/find-product/{email}")
    public StandardResponseDTO findProductShoppingCart(@Valid @PathVariable String email){
        List<FindProductShoppingCartResponseDTO> findProduct = shoppingCartService.findProductShoppingCart(email);
        return new StandardResponseDTO(false, findProduct);
    }

    @DeleteMapping("/delete-product-cart/{email}/{idProduct}")
    public StandardResponseDTO deleteProductShoppingCart(@Valid @PathVariable String email, Long idProduct){
        DeleteShoppingCartsResponseDTO deleteProduct = shoppingCartService.deleteShoppingCarts(email, idProduct);
        return new StandardResponseDTO(false, deleteProduct);
    }
}
