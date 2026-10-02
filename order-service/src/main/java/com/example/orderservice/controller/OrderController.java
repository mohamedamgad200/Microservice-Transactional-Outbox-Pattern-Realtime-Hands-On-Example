package com.example.orderservice.controller;

import com.example.orderservice.common.dto.OrderRequestDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDto));
    }
}
