package com.example.likelion13th.dto.response;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.Order;
import com.example.likelion13th.enums.DeliverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {

    private DeliverStatus deliverStatus;
    private Long orderId;
    private Long buyerId;
    private Long productId;
    private int quantity;
    private Long couponId;
    private String recipient;
    private String phoneNumber;
    private String streetAddress;
    private String detailedAddress;
    private String postalCode;

    public static OrderResponseDto fromEntity(Order order) {
        ProductOrders productOrder = order.getProductOrders().get(0);

        return OrderResponseDto.builder()
                .deliverStatus(order.getDeliverStatus())
                .orderId(order.getId())
                .buyerId(order.getBuyer().getId())
                .productId(productOrder.getProduct().getId())
                .quantity(productOrder.getQuantity())
                .couponId(order.getCoupon() != null ? order.getCoupon().getId() : null)
                .recipient(order.getRecipient())
                .phoneNumber(order.getPhoneNumber())
                .streetAddress(order.getStreetAddress())
                .detailedAddress(order.getDetailedAddress())
                .postalCode(order.getPostalCode())
                .build();
    }
}
