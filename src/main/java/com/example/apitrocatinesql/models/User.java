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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private Boolean admin;

    @Column(unique = true)
    private String nickname;

    @Column(name = "password")
    private String password;

    // Relacionamentos

    @ManyToMany
    @JoinTable(
            name = "adresses_users",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_adress")
    )
    private Set<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Phone> phones;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToMany
    @JoinTable(
            name = "users_push",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_push")
    )
    private Set<Push> pushes;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> favoriteProducts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<HistoryShoppingCart> historyShoppingCarts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SavedCard> savedCards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Highlight> highlights;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Trocadinha> trocadinhas;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<HistoryTrocadinha> historyTrocadinhas;

}