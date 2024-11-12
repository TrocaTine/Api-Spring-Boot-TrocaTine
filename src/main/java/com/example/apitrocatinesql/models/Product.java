package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Schema(description = "Product entity, including details like name, description, value, and relationships with other entities.")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    @Schema(description = "Unique identifier of the product", example = "1")
    private Long idProduct;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Product name is required.")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters.")
    @Schema(description = "Name of the product", example = "Toy Car")
    private String name;

    @Column(name = "description")
    @Size(max = 500, message = "Product description must be less than 500 characters.")
    @Schema(description = "Description of the product", example = "A red toy car suitable for kids.")
    private String description;

    @Column(name = "value")
    @NotNull(message = "Product value is required.")
    @Schema(description = "Price of the product", example = "25.99")
    private BigDecimal value;

    @Column(name = "stock", nullable = false)
    @NotNull(message = "Stock count is required.")
    @Schema(description = "Stock quantity of the product", example = "10")
    private Long stock = 1L;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    @Schema(description = "Date when the product was created", example = "2023-01-01")
    private LocalDate createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    @Schema(description = "Date when the product was last updated", example = "2023-01-10")
    private LocalDate updateAt;

    @Column(name = "flag_trade")
    @Schema(description = "Indicates if the product is available for trade", example = "true")
    private Boolean flagTrade = true;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "User associated with the product is required.")
    @Schema(description = "User who listed the product")
    private User user;

    @OneToMany(mappedBy = "product")
    @Schema(description = "Orders associated with the product")
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "products_tags",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    @Schema(description = "Tags associated with the product")
    private Set<Tag> tags;

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    @Schema(description = "Categories associated with the product")
    private Set<Category> categories;

    @ManyToMany(mappedBy = "favoriteProducts")
    @Schema(description = "Users who favorited this product")
    private Set<User> usersFavorited;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(description = "Shopping carts containing the product")
    private Set<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(description = "Shopping cart history entries containing the product")
    private Set<HistoryShoppingCart> historyShoppingCarts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(description = "Highlights for the product")
    private Set<Highlight> highlights;
}
