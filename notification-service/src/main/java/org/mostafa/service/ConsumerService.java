package org.mostafa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mostafa.model.OrderPlaceEventModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/25/2024 11:50 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${cluster.topic.name}")
    public void listen(OrderPlaceEventModel eventModel){
        log.info("Got Message from order-placed topic {}", eventModel);
        notificationService.send(eventModel);
    }
}
