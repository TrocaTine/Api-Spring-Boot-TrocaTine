package com.example.apitrocatinesql.models.DTO.requestDTO;

import com.example.apitrocatinesql.models.DTO.TagDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Data Transfer Object for editing an existing product")
public record EditProductRequestDTO(
        @NotNull(message = "Product ID cannot be null.")
        @Schema(description = "Unique identifier for the product", example = "12345")
        Long id,

        @NotNull(message = "Product name cannot be null.")
        @Schema(description = "Name of the product", example = "Blue T-shirt")
        String name,

        @NotNull(message = "Product description cannot be null.")
        @Schema(description = "Detailed description of the product", example = "A comfortable blue T-shirt made from cotton")
        String description,

        @NotNull(message = "Product value cannot be null.")
        @Schema(description = "Price of the product", example = "29.99")
        BigDecimal value,

        @NotNull(message = "Stock quantity cannot be null.")
        @Schema(description = "Available stock quantity", example = "100")
        Long stock,

        @NotNull(message = "Trade flag cannot be null.")
        @Schema(description = "Indicates if the product is available for trade", example = "true")
        Boolean flagTrade,

        @NotNull(message = "Product tags cannot be null.")
        @Schema(description = "List of tags associated with the product", example = "[{\"name\": \"clothing\", \"type\": \"category\"}]")
        List<TagDTO> tags,

        @NotNull(message = "Categories cannot be null.")
        @Schema(description = "List of categories associated with the product", example = "[\"T-shirts\", \"Clothing\"]")
        List<String> categories
) {
}
