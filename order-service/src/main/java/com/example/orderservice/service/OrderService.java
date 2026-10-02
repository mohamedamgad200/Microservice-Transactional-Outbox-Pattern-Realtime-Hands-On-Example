package com.example.orderservice.service;

import com.example.orderservice.common.dto.OrderRequestDto;
import com.example.orderservice.common.mapper.OrderDTOtoEntityMapper;
import com.example.orderservice.common.mapper.OrderEntityToOutboxEntityMapper;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.Outbox;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDTOtoEntityMapper orderDTOtoEntityMapper;
    private final OrderEntityToOutboxEntityMapper orderEntityToOutboxEntityMapper;
    private final OutboxRepository outboxRepository;

    @Transactional
    public Order createOrder(OrderRequestDto orderRequestDto) {

        Order order = orderDTOtoEntityMapper.map(orderRequestDto);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outboxRepository.save(outbox);

        return order;
    }
}
