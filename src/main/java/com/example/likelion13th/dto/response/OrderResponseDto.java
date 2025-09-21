package com.example.likelion13th.dto.response;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private Long buyerId;
    private Long productId;
    private int quantity;
    private Long couponId;
    private String recipient;
    private String phonenumber;
    private String streetaddress;
    private String detailedaddress;
    private String postalCode;

    public static OrderResponseDto fromEntity(Orders orders) {
        ProductOrders productOrder = orders.getProductOrders().get(0);

        return OrderResponseDto.builder()
                .orderId(orders.getId())
                .buyerId(orders.getBuyer().getId())
                .productId(productOrder.getProduct().getId())
                .quantity(productOrder.getQuantity())
                .couponId(orders.getCoupon() != null ? orders.getCoupon().getId() : null)
                .recipient(orders.getRecipient())
                .phonenumber(orders.getPhonenumber())
                .streetaddress(orders.getStreetaddress())
                .detailedaddress(orders.getDetailedaddress())
                .postalCode(orders.getPostalCode())
                .build();
    }


}
