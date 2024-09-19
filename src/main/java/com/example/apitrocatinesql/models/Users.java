package com.example.apitrocatinesql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    private Integer id_user;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String cpf;
    private Date birth_date;
    private boolean admin;
    private String nickname;

}
