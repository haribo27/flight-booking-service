package ru.zubcov.flightbookingservice.apigatewayservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.apigatewayservice.messaging.BookingKafkaProducer;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.UnauthorizedExceptionDTO;
import ru.zubcov.flightbookingservice.commondto.UserResponseDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingClient {

    private final BookingKafkaProducer kafkaProducer;
    private final UserClient userClient;

    public BookingRequestDTO handleAndSendBookingRequest(BookingRequestDTO request, long userId) {
        log.debug("Map booking request to Booking response");
        checkIsUserExists(userId);
        request.setUserId(userId);
        log.info("Set user id {} to booking request", userId);
        sendBookingToBookingService(request);
        log.info("Send booking request to booking service");
        return request;
    }

    private void checkIsUserExists(long userId) {
        log.info("Check that user with id: {} exists", userId);
        ResponseEntity<UserResponseDTO> userResponse = userClient.findUserById(userId);
        if (userResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new UnauthorizedExceptionDTO("You must be register to create new booking. " +
                    "User with id: " + userId + " not found");
        }
    }

    private void sendBookingToBookingService(BookingRequestDTO request) {
        kafkaProducer.sendBookingToOrchestrator(request);
    }
}
