package com.example.apitrocatinesql.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value ;

    private Long stock = 1L;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private LocalDate updateAt ;

    @Column(name = "flag_trade")
    private Boolean flagTrade = true;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "products_tags",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<Tag> tags;

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private Set<Category> categories;

    @ManyToMany(mappedBy = "favoriteProducts")
    private Set<User> usersFavorited;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<HistoryShoppingCart> historyShoppingCarts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Highlight> highlights;

}

