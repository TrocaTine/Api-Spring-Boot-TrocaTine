package com.example.apitrocatinesql.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "push")
public class Push {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_push")
    private Long idPush;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToMany
    @JoinTable(
            name = "users_push",
            joinColumns = @JoinColumn(name = "id_push"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<User> users;


}

