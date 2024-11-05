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
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Details of highlighted products, including payment information and expiration dates.")
@Table(name = "highlights")
public class Highlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_highlight")
    @Schema(description = "Unique identifier of the highlight", example = "123")
    private Long idHighlight;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "User is required for a highlight entry.")
    @Schema(description = "User who created the highlight")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    @NotNull(message = "Product is required for a highlight entry.")
    @Schema(description = "Highlighted product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_saved_card")
    @Schema(description = "Saved card used for the highlight transaction")
    private SavedCard savedCard;

    @Column(name = "payment_type", nullable = false)
    @NotBlank(message = "Payment type is required.")
    @Schema(description = "Type of payment used", example = "Credit Card")
    private String paymentType;

    @Column(name = "value", nullable = false)
    @Min(value = 1, message = "Value must be greater than 0.")
    @Schema(description = "Amount paid for the highlight", example = "50")
    private BigDecimal value;

    @Column(name = "accounted_at", nullable = false)
    @NotNull(message = "Accounted date is required.")
    @Schema(description = "Date the payment was accounted", example = "2024-11-05")
    private LocalDate accountedAt;

    @Column(name = "expirantion_at", nullable = false)
    @NotNull(message = "Expiration date is required.")
    @Schema(description = "Date the highlight will expire", example = "2025-01-05")
    private LocalDate expirantionAt;
}
