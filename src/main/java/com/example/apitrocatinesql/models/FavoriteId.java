package com.example.apitrocatinesql.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FavoriteId {

    private Long idUser;
    private Long idProduct;


}