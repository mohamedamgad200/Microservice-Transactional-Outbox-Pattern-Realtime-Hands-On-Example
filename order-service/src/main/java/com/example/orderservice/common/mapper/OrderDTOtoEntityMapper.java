package com.example.orderservice.common.mapper;

import com.example.orderservice.common.dto.OrderRequestDto;
import com.example.orderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderDTOtoEntityMapper {
    public Order map(OrderRequestDto orderRequestDTO) {
        return Order.builder()
                .customerId(orderRequestDTO.getCustomerId())
                .name(orderRequestDTO.getName())
                .productType(orderRequestDTO.getProductType())
                .quantity(orderRequestDTO.getQuantity())
                .price(orderRequestDTO.getPrice())
                .orderDate(new Date())
                .build();
    }
}
