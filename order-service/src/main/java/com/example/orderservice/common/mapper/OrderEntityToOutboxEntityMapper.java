package com.example.orderservice.common.mapper;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.Outbox;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderEntityToOutboxEntityMapper {

    @SneakyThrows
    public Outbox map(Order order) {
        return Outbox.builder()
                .aggregateId(order.getId().toString())
                .payload(new ObjectMapper().writeValueAsString(order))
                .createdAt(new Date())
                .processed(false)
                .build();
    }
}
