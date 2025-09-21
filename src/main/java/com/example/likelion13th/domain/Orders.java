package com.example.likelion13th.domain;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.enums.DeliverStatus;
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
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliverStatus deliverStatus; // 배송상태

    @ManyToOne
    @JoinColumn(name ="buyer_id")
    private Member buyer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<ProductOrders> productOrders;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private Coupon coupon;

    private String recipient;
    private String phonenumber;
    private String streetaddress;
    private String detailedaddress;
    private String postalCode;

    public void update(DeliverStatus deliverStatus, String recipient, String phonenumber, String streetaddress,
    String detailedaddress, String postalCode) {
        this.deliverStatus = deliverStatus;
        this.recipient = recipient;
        this.phonenumber = phonenumber;
        this.streetaddress = streetaddress;
        this.detailedaddress = detailedaddress;
        this.postalCode = postalCode;
    }
}
