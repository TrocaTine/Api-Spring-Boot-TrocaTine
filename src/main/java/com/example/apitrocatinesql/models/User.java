package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "first_name", nullable = false)
    @NotNull(message = "First name must not be null")
    @Size(min = 2, message = "First name must have at least 2 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull(message = "Last name must not be null")
    @Size(min = 2, message = "Last name must have at least 2 characters")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "Email must not be null")
    private String email;

    @Column(name = "cpf", nullable = false, unique = true)
    @NotNull(message = "CPF must not be null")
    private String cpf;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "admin")
    private Boolean admin;

    @Column(unique = true)
    @Size(min = 2, message = "Nickname must have at least 2 characters")
    private String nickname;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password must not be null")
    @Size(min = 2, message = "Password must have at least 2 characters")
    private String password;

    // Relacionamentos

    @ManyToMany
    @JoinTable(
            name = "addresses_users",
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
