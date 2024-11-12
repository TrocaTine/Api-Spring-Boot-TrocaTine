package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.AddProductShoppingCartResquestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.AddProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.DeleteShoppingCartsResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.ShoppingCartService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
@AllArgsConstructor
@Tag(name = "Shopping Cart Controller", description = "Controller responsible for managing the shopping cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Add product to shopping cart", description = "Adds a product to the shopping cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added product to shopping cart"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add-product")
    public StandardResponseDTO addProductToShoppingCart(@Valid @RequestBody AddProductShoppingCartResquestDTO request) {
        AddProductShoppingCartResponseDTO response = shoppingCartService.addProductShoppingCart(request);
        return new StandardResponseDTO(false, response);
    }

    @Operation(summary = "Find products in shopping cart by email", description = "Retrieves a list of products in the shopping cart for the specified email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products in shopping cart"),
            @ApiResponse(responseCode = "404", description = "No products found for the given email"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product/{email}")
    public StandardResponseDTO findProductsInShoppingCart(@Valid @PathVariable String email) {
        List<FindProductShoppingCartResponseDTO> products = shoppingCartService.findProductShoppingCart(email);
        return new StandardResponseDTO(false, products);
    }

    @Operation(summary = "Delete product from shopping cart", description = "Deletes a specific product from the shopping cart based on email and product ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted product from shopping cart"),
            @ApiResponse(responseCode = "404", description = "Product not found in the shopping cart"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete-product-cart/{email}/{idProduct}")
    public StandardResponseDTO deleteProductFromShoppingCart(@Valid @PathVariable String email, @PathVariable Long idProduct) {
        DeleteShoppingCartsResponseDTO response = shoppingCartService.deleteShoppingCarts(email, idProduct);
        return new StandardResponseDTO(false, response);
    }
}
