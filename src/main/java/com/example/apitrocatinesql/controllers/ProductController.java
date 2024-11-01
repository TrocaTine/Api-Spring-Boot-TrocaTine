package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.DTO.requestDTO.*;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.spi.MidiDeviceProvider;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/find-product-card")
    public StandardResponseDTO findProductCard(){
        List<FindProductCardResponseDTO> productCardResponseDTO = productService.findProductCard();
        return new StandardResponseDTO(false, productCardResponseDTO);
    }

    @GetMapping("/find-product-card-trade")
    public StandardResponseDTO findProductCardTrade(){
        List<FindProductCardTradeResponseDTO> productCardResponseDTO = productService.findProductCardTrade();
        return new StandardResponseDTO(false, productCardResponseDTO);
    }

    @GetMapping("/find-product-card-buy")
    public StandardResponseDTO findProductCardBuy(){
        List<FindProductCardBuyResponseDTO> productCardBuyResponseDTO = productService.findProductCardBuy();
        return new StandardResponseDTO(false, productCardBuyResponseDTO);
    }

    @GetMapping("/find-product-card-tag/{tag}")
    public StandardResponseDTO findProductCardByTag(@Valid @PathVariable String tag){
        List<FindProductCardTagResponseDTO> productCardTagResponseDTO = productService.findProductCardByTag(tag);
        return new StandardResponseDTO(false, productCardTagResponseDTO);
    }
    @GetMapping("/find-product-card-category/{category}")
    public StandardResponseDTO findProductCardByCategory(@Valid @PathVariable String category){
        List<FindProductCardCategoryResponseDTO> productCardCategoryResponseDTO = productService.findProductCardByCategory(category);
        return new StandardResponseDTO(false, productCardCategoryResponseDTO);
    }
    @GetMapping("/find-product-card-name/{name}")
    public StandardResponseDTO findProductCardByName(@Valid @PathVariable String name){
        List<FindProductCardNameResponseDTO> productCardNameResponseDTO = productService.findProductCardByName(name);
        return new StandardResponseDTO(false, productCardNameResponseDTO);
    }
    @GetMapping("/find-product-card-list-tag/{tag}")
    public StandardResponseDTO findProductCardByLitTag(@Valid @PathVariable List<String> tag){
        List<FindProductCardListTagResponseDTO> productCardByListTag= productService.findProductCardByListTag(tag);
        return new StandardResponseDTO(false, productCardByListTag);
    }
    @GetMapping("/find-product-information/{id}")
    public StandardResponseDTO findProductInformation(@Valid @PathVariable Long id){
        FindProductInformationResponseDTO product = productService.findProductInformation(id);
        return new StandardResponseDTO(false, product);
    }

    @GetMapping("/find-product-user/{email}")
    public StandardResponseDTO findProductByUser(@Valid @PathVariable String email){
        List<FindProductByUserResponseDTO> productByUserResponseDTO = productService.findPorductByUser(email);
        return new StandardResponseDTO(false, productByUserResponseDTO);
    }

    @DeleteMapping("/delete-product/{id}")
    public StandardResponseDTO deleteProduct(@Valid @PathVariable Long id){
        DeleteProductResponseDTO deleteProductResponseDTO = productService.deleteProduct(id);
        return new StandardResponseDTO(false, deleteProductResponseDTO);
    }

    @PostMapping("/save-product")
    public StandardResponseDTO saveProduct(@Valid @RequestBody SaveProductRequestDTO product){
        SaveProductResponseDTO saved = productService.saveProduct(product);
        return new StandardResponseDTO(false, saved);
    }

    @PutMapping("/edit-product")
    public StandardResponseDTO editProduct(@Valid @RequestBody EditProductRequestDTO product){
        EditProductResponseDTO saved = productService.editProduct(product);
        return new StandardResponseDTO(false, saved);
    }

    @GetMapping("/find-product-id/{productId}")
    public boolean checkProductExist(@PathVariable Long productId ){
        boolean productExist = productService.checkProductExist(productId);
        return productExist;
    }





}
