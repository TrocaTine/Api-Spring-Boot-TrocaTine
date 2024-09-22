package com.example.apitrocatinesql.models;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class FavoriteId implements Serializable {

    private Long idUser;
    private Long idProduct;


}

