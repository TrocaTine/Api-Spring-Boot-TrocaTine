package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.models.Users;
import com.example.apitrocatinesql.repositories.UsersRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository userReposiroty;
    public CustomUserDetailService( UsersRepository userReposiroty){
        this.userReposiroty = userReposiroty;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Users users = userReposiroty.findByEmail(email);
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

