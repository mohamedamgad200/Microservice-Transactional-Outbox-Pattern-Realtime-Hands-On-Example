package com.example.orderpoller.repository;


import com.example.orderpoller.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {

    //un-processed records
    List<Outbox> findByProcessedFalse();
}
