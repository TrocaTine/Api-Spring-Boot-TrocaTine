package com.example.apitrocatinesql.repositories;

import com.example.apitrocatinesql.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
