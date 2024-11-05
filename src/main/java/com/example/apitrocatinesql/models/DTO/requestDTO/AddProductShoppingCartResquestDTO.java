package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Data Transfer Object for adding a product to the shopping cart")
public record AddProductShoppingCartResquestDTO(
        @NotNull(message = "Product ID cannot be null.")
        @Schema(description = "ID of the product to be added to the shopping cart", example = "1234")
        long idProduct,

        @NotNull(message = "Quantity cannot be null.")
        @Schema(description = "Quantity of the product to be added", example = "2")
        int quantity,

        @NotNull(message = "Email cannot be null.")
        @Schema(description = "User's email address", example = "john.doe@example.com")
        String email
) {
}
