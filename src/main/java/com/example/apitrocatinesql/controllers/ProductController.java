package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.DTO.requestDTO.*;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.spi.MidiDeviceProvider;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Tag(name = "Product Controller", description = "Controller responsible for managing product cards")
public class ProductController {

    private ProductService productService;

    @Operation(summary = "Find all product cards", description = "Returns a list of all product cards.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of product cards"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card")
    public StandardResponseDTO findProductCard(){
        List<FindProductCardResponseDTO> productCardResponseDTO = productService.findProductCard();
        return new StandardResponseDTO(false, productCardResponseDTO);
    }

    @Operation(summary = "Find product cards available for trade", description = "Returns a list of product cards available for trade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of tradeable product cards"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card-trade")
    public StandardResponseDTO findProductCardTrade(){
        List<FindProductCardTradeResponseDTO> productCardResponseDTO = productService.findProductCardTrade();
        return new StandardResponseDTO(false, productCardResponseDTO);
    }

    @Operation(summary = "Find product cards available for purchase", description = "Returns a list of product cards available for purchase.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of purchasable product cards"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card-buy")
    public StandardResponseDTO findProductCardBuy(){
        List<FindProductCardBuyResponseDTO> productCardBuyResponseDTO = productService.findProductCardBuy();
        return new StandardResponseDTO(false, productCardBuyResponseDTO);
    }

    @Operation(summary = "Find product cards by tag", description = "Returns a list of product cards filtered by tag.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of product cards by tag"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card-tag/{tag}")
    public StandardResponseDTO findProductCardByTag(@Valid @PathVariable String tag){
        List<FindProductCardTagResponseDTO> productCardTagResponseDTO = productService.findProductCardByTag(tag);
        return new StandardResponseDTO(false, productCardTagResponseDTO);
    }

    @Operation(summary = "Find product cards by category", description = "Returns a list of product cards filtered by category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of product cards by category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card-category/{category}")
    public StandardResponseDTO findProductCardByCategory(@Valid @PathVariable String category){
        List<FindProductCardCategoryResponseDTO> productCardCategoryResponseDTO = productService.findProductCardByCategory(category);
        return new StandardResponseDTO(false, productCardCategoryResponseDTO);
    }

    @Operation(summary = "Find product cards by name", description = "Returns a list of product cards filtered by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of product cards by name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card-name/{name}")
    public StandardResponseDTO findProductCardByName(@Valid @PathVariable String name){
        List<FindProductCardNameResponseDTO> productCardNameResponseDTO = productService.findProductCardByName(name);
        return new StandardResponseDTO(false, productCardNameResponseDTO);
    }

    @Operation(summary = "Find product cards by list of tags", description = "Returns a list of product cards filtered by a list of tags.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of product cards by list of tags"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-card-list-tag/{tag}")
    public StandardResponseDTO findProductCardByListTag(@Valid @PathVariable List<String> tag){
        List<FindProductCardListTagResponseDTO> productCardByListTag = productService.findProductCardByListTag(tag);
        return new StandardResponseDTO(false, productCardByListTag);
    }

    @Operation(summary = "Find product information by ID", description = "Returns detailed information about a specific product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned product information"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-information/{id}")
    public StandardResponseDTO findProductInformation(@Valid @PathVariable Long id){
        FindProductInformationResponseDTO product = productService.findProductInformation(id);
        return new StandardResponseDTO(false, product);
    }

    @Operation(summary = "Find products by user email", description = "Returns a list of products associated with the user email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of user products"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-product-user/{email}")
    public StandardResponseDTO findProductByUser(@Valid @PathVariable String email){
        List<FindProductByUserResponseDTO> productByUserResponseDTO = productService.findPorductByUser(email);
        return new StandardResponseDTO(false, productByUserResponseDTO);
    }

    @Operation(summary = "Delete a product by ID", description = "Deletes a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the product"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete-product/{id}")
    public StandardResponseDTO deleteProduct(@Valid @PathVariable Long id){
        DeleteProductResponseDTO deleteProductResponseDTO = productService.deleteProduct(id);
        return new StandardResponseDTO(false, deleteProductResponseDTO);
    }

    @Operation(summary = "Save a new product", description = "Saves a new product with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved the product"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/save-product")
    public StandardResponseDTO saveProduct(@Valid @RequestBody SaveProductRequestDTO product){
        SaveProductResponseDTO saved = productService.saveProduct(product);
        return new StandardResponseDTO(false, saved);
    }

    @Operation(summary = "Edit an existing product", description = "Edits an existing product with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited the product"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/edit-product")
    public StandardResponseDTO editProduct(@Valid @RequestBody EditProductRequestDTO product){
        EditProductResponseDTO saved = productService.editProduct(product);
        return new StandardResponseDTO(false, saved);
    }

    @Operation(summary = "Check if product exists by ID", description = "Checks if a product exists based on the provided product ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product exists"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/find-product-id/{productId}")
    public boolean checkProductExist(@PathVariable Long productId){
        return productService.checkProductExist(productId);
    }
}
