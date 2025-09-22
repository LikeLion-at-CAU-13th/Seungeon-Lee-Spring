package com.example.likelion13th.domain.Mapping;

import com.example.likelion13th.domain.Order;
import com.example.likelion13th.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    private Integer quantity; // 구매 수량
}
