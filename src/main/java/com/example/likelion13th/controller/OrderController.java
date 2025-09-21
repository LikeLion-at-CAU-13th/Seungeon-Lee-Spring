package com.example.likelion13th.controller;

import com.example.likelion13th.dto.request.*;
import com.example.likelion13th.dto.response.OrderResponseDto;
import com.example.likelion13th.dto.response.ProductResponseDto;
import com.example.likelion13th.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders") // 공통 경로
public class OrderController {

    private final OrderService orderService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<OrderResponseDto> createProduct(@RequestBody OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllProducts() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // 특정 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrdersById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateProduct(@PathVariable Long id,
                                                            @RequestBody OrderUpdateRequestDto dto) {
        return ResponseEntity.ok(orderService.UpdateOrders(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id,
                                                @RequestBody OrderDeleteRequestDto dto) {
        orderService.deleteOrders(id, dto);
        return ResponseEntity.ok("주문 정보가 성공적으로 삭제되었습니다.");
    }
}
