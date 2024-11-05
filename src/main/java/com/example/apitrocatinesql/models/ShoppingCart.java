package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Schema(description = "Represents a shopping cart entry for a user.")
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shopping_cart")
    @Schema(description = "Unique identifier for the shopping cart entry", example = "1")
    private Long idShoppingCart;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "User is required.")
    @Schema(description = "User associated with this shopping cart entry")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    @NotNull(message = "Product is required.")
    @Schema(description = "Product associated with this shopping cart entry")
    private Product product;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity is required.")
    @Min(value = 1, message = "Quantity must be at least 1.")
    @Schema(description = "Quantity of the product in the shopping cart", example = "2")
    private Integer quantity;

    @Column(name = "value", nullable = false)
    @NotNull(message = "Value is required.")
    @Schema(description = "Total value of the shopping cart entry", example = "19.99")
    private BigDecimal value;

}
