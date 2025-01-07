package ru.zubcov.flightbookingservice.bookingservice.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingMessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${booking-fail-topic}")
    private String bookingFailTopic;

    public void sendMessageToOrchestrator() {
        // тут могут отправляться события в оркестратор для других микросервисов
    }
}
