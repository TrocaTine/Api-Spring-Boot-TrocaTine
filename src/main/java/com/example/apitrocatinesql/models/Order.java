package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Details about the order including payment type, total value, and associated product.")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    @Schema(description = "Unique identifier for the order", example = "101")
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_shopping_cart_old")
    @Schema(description = "Old shopping cart associated with the order")
    private HistoryShoppingCart shoppingCartOld;

    @ManyToOne
    @JoinColumn(name = "id_saved_card")
    @Schema(description = "Saved payment card used in the order")
    private SavedCard savedCard;

    @Column(name = "payment_type", nullable = false)
    @NotBlank(message = "Payment type is required.")
    @Schema(description = "Type of payment used for the order", example = "Credit Card")
    private String paymentType;

    @Column(name = "total_value", nullable = false)
    @NotNull(message = "Total value is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total value must be greater than 0.")
    @Schema(description = "Total value of the order", example = "100.50")
    private BigDecimal totalValue;

    @Column(name = "accounted_at", nullable = false)
    @NotNull(message = "Accounting date is required.")
    @Schema(description = "Date when the order was accounted", example = "2024-12-15")
    private LocalDate accountedAt;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    @NotNull(message = "Product is required for the order.")
    @Schema(description = "Product associated with the order")
    private Product product;
}
