package ru.zubcov.flightbookingservice.apigatewayservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.apigatewayservice.mapper.BookingMapper;
import ru.zubcov.flightbookingservice.apigatewayservice.messaging.BookingKafkaProducer;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingResponseDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayService {

    private final BookingKafkaProducer kafkaProducer;
    private final BookingMapper mapper;

    public BookingResponseDTO handleAndSendBookingRequest(BookingRequestDTO request) {
        BookingResponseDTO booking = mapper.mapToBookingDto(request);
        log.debug("Map booking request to Booking response");
        String bookingCode = generateBookingCode();
        log.info("Generate unique booking code {}", bookingCode);
        booking.setBookingCode(bookingCode);
        sendBookingToBookingService(booking);
        log.info("Send booking request to booking service");
        return booking;
    }

    private String generateBookingCode() {
        log.debug("Getting random UUID for booking request");
        return UUID.randomUUID().toString();
    }

    private void sendBookingToBookingService(BookingResponseDTO bookingRequest) {
        kafkaProducer.sendBookingToOrchestrator(bookingRequest);
    }
}
