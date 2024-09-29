package com.example.apitrocatinesql.models;

import jakarta.persistence.*;
import lombok.*;

import javax.management.ConstructorParameters;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long idPhone;

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;


    public Phone(String number, User user){
        this.number = number;
        this.user = user;
    }


}
