package ru.zubcov.flightbookingservice.orchestratorservice.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;
import ru.zubcov.flightbookingservice.orchestratorservice.service.OrchestratorService;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorConsumer {

    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "${booking-request-topic}", groupId = "bookings")
    public void handleBookingRequest(BookingRequestDTO booking) {
        log.info("Message consumed: {}", booking);
        orchestratorService.bookingProcess(booking);
    }

    @KafkaListener(topics = "${booking-request-status-topic}", groupId = "bookings")
    public void handleUpdateBookingStatus(BookingStatusUpdateDTO bookingStatusUpdateRequest) {
        log.info("Message consumed: {}", bookingStatusUpdateRequest);
        orchestratorService.bookingUpdateStatus(bookingStatusUpdateRequest);
    }
}
