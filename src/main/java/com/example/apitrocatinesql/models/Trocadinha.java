package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trocadinhas")
public class Trocadinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trocadinha")
    private Long idTrocadinha;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "number_trocadinha")
    @Min(value = 0, message = "Number of trocadinhas must be at least 0")
    private Integer numberTrocadinha;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "last_atualization")
    private LocalDate lastAtualization;
}
