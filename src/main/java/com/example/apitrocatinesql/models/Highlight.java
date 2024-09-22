package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
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
@Table(name = "highlights")
public class Highlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_highlight")
    private Long idHighlight;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_saved_card")
    private SavedCard savedCard;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "accounted_at")
    private LocalDate accountedAt;

    @Column(name = "expirantion_at")
    private LocalDate expirantionAt;

}

