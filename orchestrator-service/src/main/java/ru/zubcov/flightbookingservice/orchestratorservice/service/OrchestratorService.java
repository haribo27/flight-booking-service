package ru.zubcov.flightbookingservice.orchestratorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${booking-process-topic}")
    private String bookingProcessTopic;
    @Value("${booking-process-status-topic}")
    private String bookingStatusUpdateTopic;

    public void bookingProcess(BookingRequestDTO booking) {
        log.info("Send new booking request to booking-service {}", booking);
        kafkaTemplate.send(bookingProcessTopic, booking);
    }

    public void bookingUpdateStatus(BookingStatusUpdateDTO bookingStatusUpdateRequest) {
        log.info("Send update booking request to booking-service {}", bookingStatusUpdateRequest);
        kafkaTemplate.send(bookingStatusUpdateTopic, bookingStatusUpdateRequest);
    }

    /*@KafkaListener(topics = "${booking-fail-topic}", groupId = "orchestrator-group")
    public void bookingFail() {

    }*/
}
