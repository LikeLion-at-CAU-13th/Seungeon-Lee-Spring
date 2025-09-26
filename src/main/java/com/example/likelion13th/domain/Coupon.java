package com.example.likelion13th.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description; // 쿠폰 내용
    private Integer discountRate; // 할인율
    private Integer minPrice; // 쿠폰 적용 최소 금액
    private Boolean isUsed; // 쿠폰 사용 여부

    @OneToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

}
