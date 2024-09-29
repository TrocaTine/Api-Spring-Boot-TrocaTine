package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Address;
import com.example.apitrocatinesql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressByUsers(User user);


}
