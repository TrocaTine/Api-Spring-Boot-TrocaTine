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

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Historical record of Trocadinhas, tracking user actions, position, and validity details.")
@Table(name = "history_trocadinhas")
public class HistoryTrocadinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trocadinha")
    @Schema(description = "Unique identifier of the Trocadinha history entry", example = "1001")
    private Long idTrocadinha;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "User is required for Trocadinha history entry.")
    @Schema(description = "User associated with the Trocadinha history")
    private User user;

    @Column(name = "position", nullable = false)
    @NotBlank(message = "Position is required.")
    @Schema(description = "Position in the Trocadinha ranking or structure", example = "Top 10")
    private String position;

    @Column(name = "number_trocadinha", nullable = false)
    @Min(value = 1, message = "The number of Trocadinhas must be at least 1.")
    @Schema(description = "The number of Trocadinhas associated with this entry", example = "5")
    private Integer numberTrocadinha;

    @Column(name = "expiration_date", nullable = false)
    @NotNull(message = "Expiration date is required.")
    @Schema(description = "Expiration date for the Trocadinhas", example = "2024-12-31")
    private LocalDate expirationDate;

    @Column(name = "last_atualization", nullable = false)
    @NotNull(message = "Last update date is required.")
    @Schema(description = "The date of the last update to this entry", example = "2024-01-15")
    private LocalDate lastAtualization;
}
