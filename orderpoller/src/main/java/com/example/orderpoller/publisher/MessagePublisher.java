package com.example.orderpoller.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${order.poller.topic.name}")
    private String topicName;

    public void publish(String payload) {
        CompletableFuture<SendResult<String, String>> feature = kafkaTemplate.send(topicName, payload);
        feature.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message {} with offset={}", payload, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message {} due to {}", payload, ex.getMessage());
            }
        });
    }
}
