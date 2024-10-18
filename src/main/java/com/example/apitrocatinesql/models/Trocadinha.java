package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private Integer numberTrocadinha;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "last_atualization")
    private LocalDate lastAtualization;


}

