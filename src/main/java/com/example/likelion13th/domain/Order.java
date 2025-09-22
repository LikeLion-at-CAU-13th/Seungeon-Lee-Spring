package com.example.likelion13th.domain;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.enums.DeliverStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"ORDER\"")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliverStatus deliverStatus; // 배송상태

    @ManyToOne
    @JoinColumn(name ="buyer_id")
    private Member buyer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductOrders> productOrders = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Coupon coupon;

    private String recipient;
    private String phoneNumber;
    private String streetAddress;
    private String detailedAddress;
    private String postalCode;

    public void update(
            DeliverStatus deliverStatus,
            String recipient,
            String phoneNumber,
            String streetAddress,
            String detailedAddress,
            String postalCode
    ) {
        this.deliverStatus = deliverStatus;
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.detailedAddress = detailedAddress;
        this.postalCode = postalCode;
    }
}
