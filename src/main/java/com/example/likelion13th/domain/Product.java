package com.example.likelion13th.domain;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 상품이름
    @Column(nullable = false)
    private Integer price; // 상품가격
    @Column(nullable = false)
    private Integer stock; // 상품 재고
    @Column(nullable = false)
    private String description; // 상품 정보

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOrders> productOrders;


    public void reduceStock(int amount) {
        this.stock -= amount;
    }

    public void update(String name, Integer price, Integer stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
