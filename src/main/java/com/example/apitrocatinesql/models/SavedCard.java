package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
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
@Table(name = "saved_cards")
public class SavedCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saved_card")
    private Long idSavedCard;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv")
    private Integer cvv;

    @OneToMany(mappedBy = "savedCard", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(mappedBy = "savedCard", cascade = CascadeType.ALL)
    private Set<Highlight> highlights;

}
