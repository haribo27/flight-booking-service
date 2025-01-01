package ru.zubcov.flightbookingservice.apigatewayservice.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.zubcov.flightbookingservice.commondto.BookingResponseDTO;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingKafkaProducer {

    private final KafkaTemplate<String, BookingResponseDTO> kafkaTemplate;
    @Value("${booking-request-topic}")
    private String bookingRequestTopic;

    public void sendBookingToOrchestrator(BookingResponseDTO request) {
        log.info("Send booking request to booking service {}", request);
        kafkaTemplate.send(bookingRequestTopic, request);
    }
}
