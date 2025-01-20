package ru.zubcov.flightbookingservice.apigatewayservice.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;

@Component
@RequiredArgsConstructor
@Slf4j
public class GatewayKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${booking-request-topic}")
    private String bookingRequestTopic;
    @Value("${booking-request-status-topic}")
    private String bookingStatusUpdateTopic;

    public void sendNewBookingRequestToOrchestrator(BookingRequestDTO request) {
        log.info("Send booking request to booking service {}", request);
        kafkaTemplate.send(bookingRequestTopic, request);
    }

    public void sendUpdateBookingStatusToOrchestrator(BookingStatusUpdateDTO updateRequest) {
        log.info("Send request to update booking status to booking-service {}", updateRequest);
        kafkaTemplate.send(bookingStatusUpdateTopic, updateRequest);
    }
}
