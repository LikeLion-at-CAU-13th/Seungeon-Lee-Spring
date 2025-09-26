package com.example.likelion13th.service;

import com.example.likelion13th.domain.Coupon;
import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.Member;
import com.example.likelion13th.domain.Order;
import com.example.likelion13th.domain.Product;
import com.example.likelion13th.dto.request.*;
import com.example.likelion13th.dto.response.OrderResponseDto;
import com.example.likelion13th.enums.DeliverStatus;
import com.example.likelion13th.repository.CouponRepository;
import com.example.likelion13th.repository.MemberRepository;
import com.example.likelion13th.repository.OrdersRepository;
import com.example.likelion13th.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository orderRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {

        Member member = memberRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 구매자입니다."));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        Coupon coupon = null;
        if(dto.getCouponId() != null){
            coupon = couponRepository.findById(dto.getCouponId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));
        }

        Order order = dto.toEntity(member, coupon);

        ProductOrders productOrder = ProductOrders.builder()
                .product(product)
                .order(order)
                .quantity(dto.getQuantity())
                .build();

        order.getProductOrders().add(productOrder);

        Order saved = orderRepository.save(order);

        return new OrderResponseDto(
                saved.getDeliverStatus(),
                saved.getId(),
                saved.getBuyer().getId(),
                saved.getProductOrders().get(0).getProduct().getId(),
                saved.getProductOrders().get(0).getQuantity(),
                saved.getCoupon() != null ? saved.getCoupon().getId() : null,
                saved.getRecipient(),
                saved.getPhoneNumber(),
                saved.getStreetAddress(),
                saved.getDetailedAddress(),
                saved.getPostalCode()
        );
    }

    // 모든 주문 정보 가져오기
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderResponseDto::fromEntity)
                .toList();
    }

    // 특정 주문 정보 가져오기
    public OrderResponseDto getOrdersById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문정보가 존재하지 않습니다."));
        return OrderResponseDto.fromEntity(order);
    }

    @Transactional
    public OrderResponseDto UpdateOrders(Long orderId, OrderUpdateRequestDto dto) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문정보가 존재하지 않습니다."));

        if (order.getDeliverStatus() != DeliverStatus.PREPARATION) {
            throw new IllegalStateException("배송이 준비 중(PREPARATION) 상태일 때만 수정 가능합니다.");
        }

        order.update(dto.getDeliverStatus(),
                dto.getRecipient(),
                dto.getPhoneNumber(),
                dto.getStreetAddress(),
                dto.getDetailedAddress(),
                dto.getPostalCode());

        return OrderResponseDto.fromEntity(order);
    }

    @Transactional
    public void deleteOrders(Long orderId, OrderDeleteRequestDto dto) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문내역을 찾을 수 없습니다."));

        if (order.getDeliverStatus() != DeliverStatus.COMPLETED) {
            throw new IllegalStateException("배송이 완료(COMPLETED)된 주문만 삭제 가능합니다.");
        }

        orderRepository.delete(order);
    }
}
