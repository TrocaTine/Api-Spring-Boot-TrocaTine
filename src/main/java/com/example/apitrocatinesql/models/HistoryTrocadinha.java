package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
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
@Table(name = "history_trocadinhas")
public class HistoryTrocadinha {
    @Id
    @Column(name = "id_trocadinha")
    private Long idTrocadinha;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user; // Adicione esta propriedade

    @Column(name = "position")
    private String position;

    @Column(name = "number_trocadinha")
    private Integer numberTrocadinha;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "last_atualization")
    private LocalDate lastAtualization;

}
