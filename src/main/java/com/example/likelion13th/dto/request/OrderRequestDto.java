package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.Coupon;
import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.Member;
import com.example.likelion13th.domain.Order;
import com.example.likelion13th.domain.Product;
import com.example.likelion13th.enums.DeliverStatus;
import lombok.Getter;

@Getter
public class OrderRequestDto {

    private DeliverStatus deliverStatus;
    private Long buyerId;
    private Long productId;
    private Integer quantity;
    private Long couponId;
    private String recipient;
    private String phoneNumber;
    private String streetAddress;
    private String detailedAddress;
    private String postalCode;

    public Order toEntity(Member buyer, Coupon coupon) {

        Order order = Order.builder()
                .deliverStatus(this.deliverStatus)
                .buyer(buyer)
                .coupon(coupon)
                .recipient(this.recipient)
                .phoneNumber(this.phoneNumber)
                .streetAddress(this.streetAddress)
                .detailedAddress(this.detailedAddress)
                .postalCode(this.postalCode)
                .build();

        return order;
    }

}
