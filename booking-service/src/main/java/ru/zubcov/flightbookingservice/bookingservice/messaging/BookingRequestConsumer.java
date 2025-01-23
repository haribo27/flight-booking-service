package ru.zubcov.flightbookingservice.bookingservice.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.BookingRequestDTO;
import org.openapitools.model.BookingStatusUpdateDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.bookingservice.service.BookingService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingRequestConsumer {

    private final BookingService bookingService;

    @KafkaListener(topics = "${booking-process-topic}", groupId = "booking-process-group")
    public void consumeCreateBookingRequest(BookingRequestDTO request) {
        log.info("Message from orchestrator consumed: {}", request);
        bookingService.createBooking(request);
    }

    @KafkaListener(topics = "${booking-process-status-topic}", groupId = "booking-status-group")
    public void consumeUpdateBookingStatusRequest(BookingStatusUpdateDTO bookingStatusUpdateRequest) {
        log.info("Message from orchestrator consumed1: {}", bookingStatusUpdateRequest);
        bookingService.updateBookingStatus(bookingStatusUpdateRequest);
    }
}
