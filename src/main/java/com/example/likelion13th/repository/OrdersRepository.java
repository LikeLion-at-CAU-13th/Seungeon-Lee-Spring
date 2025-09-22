package com.example.likelion13th.repository;

import com.example.likelion13th.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
