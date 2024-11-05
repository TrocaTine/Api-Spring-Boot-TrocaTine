package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Data Transfer Object for finishing an order")
public record FinishedOrderRequestDTO(

        @NotNull(message = "User email cannot be null.")
        @Schema(description = "Email of the user making the order", example = "john.doe@example.com")
        String email,

        @NotNull(message = "Product ID cannot be null.")
        @Schema(description = "ID of the product being ordered", example = "1234")
        Long idProduct,

        @NotNull(message = "Card number cannot be null.")
        @Schema(description = "Number of the payment card", example = "4111111111111111")
        String numberCard,

        @NotNull(message = "Payment type cannot be null.")
        @Schema(description = "Type of payment used", example = "CREDIT_CARD")
        String paymentType,

        @NotNull(message = "Order value cannot be null.")
        @Schema(description = "Total value of the order", example = "29.99")
        BigDecimal value
) {
}
