package com.example.apitrocatinesql.models.DTO.requestDTO;

import com.example.apitrocatinesql.models.DTO.TagDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Data Transfer Object for saving a new product")
public record SaveProductRequestDTO(
        @NotNull(message = "User email cannot be null.")
        @Schema(description = "Email of the user saving the product", example = "john.doe@example.com")
        String emailUser,

        @NotNull(message = "Product name cannot be null.")
        @Schema(description = "Name of the product", example = "Children's T-shirt")
        String name,

        @NotNull(message = "Product description cannot be null.")
        @Schema(description = "Description of the product", example = "A comfortable cotton t-shirt for children.")
        String description,

        @NotNull(message = "Product value cannot be null.")
        @Schema(description = "Value of the product", example = "29.99")
        BigDecimal value,

        @NotNull(message = "Stock cannot be null.")
        @Schema(description = "Available stock of the product", example = "100")
        Long stock,

        @NotNull(message = "Trade flag cannot be null.")
        @Schema(description = "Flag indicating if the product is available for trade", example = "true")
        Boolean flagTrade,

        @NotNull(message = "Tags cannot be null.")
        @Schema(description = "List of tags associated with the product")
        List<TagDTO> tags,

        @NotNull(message = "Categories cannot be null.")
        @Schema(description = "List of categories associated with the product")
        List<String> categories
) {
}
