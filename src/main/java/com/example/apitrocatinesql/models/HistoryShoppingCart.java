package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Historical shopping cart details, capturing past purchases including user, product, quantity, and status.")
@Table(name = "history_shopping_carts")
public class HistoryShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shopping_cart_old")
    @Schema(description = "Unique identifier of the historical shopping cart entry", example = "123")
    private Long idShoppingCartOld;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "User is required for a shopping cart history entry.")
    @Schema(description = "User associated with the shopping cart history")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    @NotNull(message = "Product is required for a shopping cart history entry.")
    @Schema(description = "Product associated with the shopping cart history")
    private Product product;

    @Column(name = "quantity", nullable = false)
    @Min(value = 1, message = "Quantity must be at least 1.")
    @Schema(description = "Quantity of the product in the shopping cart", example = "3")
    private Integer quantity;

    @Column(name = "value", nullable = false)
    @Min(value = 0, message = "Value must be zero or positive.")
    @Schema(description = "Total value of the shopping cart entry", example = "49.99")
    private BigDecimal value;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status is required.")
    @Schema(description = "Status of the shopping cart entry", example = "Completed")
    private String status;
}
