package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.DTO.requestDTO.*;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping("/find-product-card")
    public StandardResponseDTO findProductCard(){
        List<FindProductCardResponseDTO> productCardResponseDTO = productService.findProductCard();
        return new StandardResponseDTO(false, productCardResponseDTO);
    }

    @PostMapping("/find-product-card-trade")
    public StandardResponseDTO findProductCardTrade(){
        List<FindProductCardTradeResponseDTO> productCardResponseDTO = productService.findProductCardTrade();
        return new StandardResponseDTO(false, productCardResponseDTO);
    }

    @PostMapping("/find-product-card-buy")
    public StandardResponseDTO findProductCardBuy(){
        List<FindProductCardBuyResponseDTO> productCardBuyResponseDTO = productService.findProductCardBuy();
        return new StandardResponseDTO(false, productCardBuyResponseDTO);
    }

    @PostMapping("/find-product-card-tag")
    public StandardResponseDTO findProductCardByTag(@Valid @RequestBody FindProductCardTagRequestDTO tag){
        List<FindProductCardTagResponseDTO> productCardTagResponseDTO = productService.findProductCardByTag(tag.tag());
        return new StandardResponseDTO(false, productCardTagResponseDTO);
    }
    @PostMapping("/find-product-card-category")
    public StandardResponseDTO findProductCardByCategory(@Valid @RequestBody FindProductCardCategoryRequestDTO category){
        List<FindProductCardCategoryResponseDTO> productCardCategoryResponseDTO = productService.findProductCardByCategory(category.category());
        return new StandardResponseDTO(false, productCardCategoryResponseDTO);
    }
    @PostMapping("/find-product-card-name")
    public StandardResponseDTO findProductCardByName(@Valid @RequestBody FindProductCardNameRequestDTO name){
        List<FindProductCardNameResponseDTO> productCardNameResponseDTO = productService.findProductCardByName(name.name());
        return new StandardResponseDTO(false, productCardNameResponseDTO);
    }
    @PostMapping("/find-product-card-list-tag")
    public StandardResponseDTO findProductCardByLitTag(@Valid @RequestBody FindProductCardListTagsRequestDTO tag){
        List<FindProductCardListTagResponseDTO> productCardByListTag= productService.findProductCardByListTag(tag.tagName());
        return new StandardResponseDTO(false, productCardByListTag);
    }
    @PostMapping("/find-product-information")
    public StandardResponseDTO findProductInformation(@Valid @RequestBody FindProductInformactionRequestDTO id){
        FindProductInformationResponseDTO product = productService.findProductInformation(id.id());
        return new StandardResponseDTO(false, product);
    }

    @PostMapping("/find-product-user")
    public StandardResponseDTO findProductByUser(@Valid @RequestBody FindProductByUserRequestDTO email){
        List<FindProductByUserResponseDTO> productByUserResponseDTO = productService.findPorductByUser(email.email());
        return new StandardResponseDTO(false, productByUserResponseDTO);
    }

    @DeleteMapping("/delete-product")
    public StandardResponseDTO deleteProduct(@Valid @RequestBody DeleteProductRequestDTO id){
        DeleteProductResponseDTO deleteProductResponseDTO = productService.deleteProduct(id.idProduct());
        return new StandardResponseDTO(false, deleteProductResponseDTO);
    }

    @PostMapping("/save-product")
    public StandardResponseDTO saveProduct(@Valid @RequestBody SaveProductRequestDTO product){
        SaveProductResponseDTO saved = productService.saveProduct(product);
        return new StandardResponseDTO(false, saved);
    }

    @PostMapping("/edit-product")
    public StandardResponseDTO editProduct(@Valid @RequestBody EditProductRequestDTO product){
        EditProductResponseDTO saved = productService.editProduct(product);
        return new StandardResponseDTO(false, saved);
    }




}
