package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Schema(description = "Composite key for the Favorite entity, containing user and product IDs.")
public class FavoriteId implements Serializable {

    @Column(name = "id_user", nullable = false)
    @Schema(description = "ID of the user who favorited the product", example = "101")
    private Long idUser;

    @Column(name = "id_product", nullable = false)
    @Schema(description = "ID of the product marked as favorite", example = "202")
    private Long idProduct;
}
