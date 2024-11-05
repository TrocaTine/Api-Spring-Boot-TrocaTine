package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Represents a saved card information for user transactions.")
@Table(name = "saved_cards")
public class SavedCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saved_card")
    @Schema(description = "Unique identifier for the saved card", example = "1")
    private Long idSavedCard;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @Schema(description = "User associated with this saved card")
    private User user;

    @Column(name = "card_number", nullable = false)
    @NotBlank(message = "Card number is required.")
    @Size(min = 16, max = 16, message = "Card number must be exactly 16 digits.")
    @Schema(description = "Credit card number", example = "1234567812345678")
    private String cardNumber;

    @Column(name = "expiration_date", nullable = false)
    @NotNull(message = "Expiration date is required.")
    @Schema(description = "Expiration date of the credit card", example = "2025-12-31")
    private LocalDate expirationDate;

    @Column(name = "cvv", nullable = false)
    @NotNull(message = "CVV is required.")
    @Schema(description = "CVV of the credit card", example = "123")
    private Integer cvv;

    @OneToMany(mappedBy = "savedCard", cascade = CascadeType.ALL)
    @Schema(description = "Orders associated with this saved card")
    private Set<Order> orders;

    @OneToMany(mappedBy = "savedCard", cascade = CascadeType.ALL)
    @Schema(description = "Highlights associated with this saved card")
    private Set<Highlight> highlights;

}
