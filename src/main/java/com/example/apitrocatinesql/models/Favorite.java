package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Schema(description = "Representation of a favorite relationship between a user and a product.")
@Table(name = "favorites")
public class Favorite {

    @EmbeddedId
    @Schema(description = "Composite ID for the favorite relationship, containing both user and product IDs")
    private FavoriteId id;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user", nullable = false)
    @Schema(description = "User who favorited the product")
    private User user;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product", nullable = false)
    @Schema(description = "Product marked as favorite by the user")
    private Product product;
}
