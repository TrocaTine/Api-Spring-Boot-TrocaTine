package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.UsersRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository userReposiroty;
    public CustomUserDetailService( UsersRepository userReposiroty){
        this.userReposiroty = userReposiroty;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User users = userReposiroty.findByEmail(email);
        List<GrantedAuthority> authorities =  Collections.emptyList();

        return new org.springframework.security.core.userdetails.User(
                users.getEmail(),
                users.getPassword(),
                true,
                true,
                true,true, authorities

        );
    }




}

