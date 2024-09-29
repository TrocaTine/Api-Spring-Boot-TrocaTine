package com.example.apitrocatinesql.models;


import jakarta.persistence.*;
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
@Table(name = "adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adress")
    private Long idAdress;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number")
    private Integer number;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "complement")
    private String complement;

    @Column(name = "cep", nullable = false)
    private String cep;

    @ManyToMany
    @JoinTable(
            name = "adresses_users",
            joinColumns = @JoinColumn(name = "id_adress"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<User> users;


}


