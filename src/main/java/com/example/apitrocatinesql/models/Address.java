package com.example.apitrocatinesql.models;


import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "User address information, details like street, city, state...")
@Table(name = "adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Address ID", example = "12345")
    @Column(name = "id_adress")
    private Long idAdress;

    @Schema(description = "Address street", example = "Rua Irineu José Bordon")
    @Column(name = "street", nullable = false)
    @Size(min = 2,message = "Address street must have a value greater than 2")
    private String street;

    @Schema(description = "Address number", example = "123")
    @Min(value = 0, message = "Address number must have a value greater than 0")
    @Column(name = "number")
    private Integer number;

    @Schema(description = "Address city", example = "São Paulo")
    @Column(name = "city", nullable = false)
    @Size(min = 2,message = "Address city must have a value greater than 2")
    private String city;

    @Schema(description = "Address state", example = "SP")
    @Column(name = "state", nullable = false)
    @Size(min = 2,message = "Address state must have a value greater than 2")
    private String state;

    @Schema(description = "Address complement", example = "Apartment 93")
    @Column(name = "complement")
    private String complement;

    @Schema(description = "Address CEP", example = "05324130")
    @Column(name = "cep", nullable = false)
    @Size(min = 8, max = 8, message = "Address state must have a value greater than 2")
    private String cep;

    @ManyToMany
    @JoinTable(
            name = "adresses_users",
            joinColumns = @JoinColumn(name = "id_adress"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    @Schema(description = "A User object with that address")
    private Set<User> users;


}


