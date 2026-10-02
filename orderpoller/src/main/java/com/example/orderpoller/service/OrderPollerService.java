package com.example.orderpoller.service;

import com.example.orderpoller.entity.Outbox;
import com.example.orderpoller.publisher.MessagePublisher;
import com.example.orderpoller.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class OrderPollerService {

    private final OutboxRepository outboxRepository;
    private final MessagePublisher messagePublisher;

    @Scheduled(fixedRate = 60000)
    public void pollOutboxMessagesAndPublish() {
        //1.fetched unprocessed record
        List<Outbox> unprocessedRecords = outboxRepository.findByProcessedFalse();
        log.info("unprocessed record count : {}", unprocessedRecords.size());
        //2.publish record to kafka/queue
        unprocessedRecords.forEach(outbox -> {
            try {
                messagePublisher.publish(outbox.getPayload());
                //update the message status to processed = true to avoid duplicate message processing
                outbox.setProcessed(true);
                outboxRepository.save(outbox);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }
}
