package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.Coupon;
import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.Member;
import com.example.likelion13th.domain.Orders;
import com.example.likelion13th.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {

    private Long buyerId;
    private Long productId;
    private int quantity;
    private Long couponId;
    private String recipient;
    private String phonenumber;
    private String streetaddress;
    private String detailedaddress;
    private String postalCode;

    public Orders toEntity(Member buyer, Product product, Coupon coupon) {

        Orders order = Orders.builder()
                .buyer(buyer)
                .coupon(coupon)
                .recipient(this.recipient)
                .phonenumber(this.phonenumber)
                .streetaddress(this.streetaddress)
                .detailedaddress(this.detailedaddress)
                .postalCode(this.postalCode)
                .build();

        ProductOrders productOrder = new ProductOrders();
        productOrder.setProduct(product);
        productOrder.setQuantity(this.quantity);
        return order;
    }

}
