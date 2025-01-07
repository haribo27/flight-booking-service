package ru.zubcov.flightbookingservice.apigatewayservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.apigatewayservice.client.BookingFeignClient;
import ru.zubcov.flightbookingservice.apigatewayservice.client.UserFeignClient;
import ru.zubcov.flightbookingservice.apigatewayservice.mapper.BookingMapper;
import ru.zubcov.flightbookingservice.apigatewayservice.messaging.GatewayKafkaProducer;
import ru.zubcov.flightbookingservice.commondto.*;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final GatewayKafkaProducer bookingKafkaProducer;
    private final BookingMapper bookingMapper;
    private final UserFeignClient userClient;
    private final BookingFeignClient bookingFeignClient;

    public BookingResponseDTO handleAndSendBookingRequest(BookingRequestDTO request, long userId) {
        isUserExists(userId);
        request.setUserId(userId);
        log.info("Set user id {} to booking request", userId);
        bookingKafkaProducer.sendNewBookingRequestToOrchestrator(request);
        log.info("Send booking request to booking service");
        return bookingMapper.mapToBookingResponseDTO(request);
    }

    public ResponseEntity<Set<BookingDTO>> getBookingInfo(long userId) {
        log.info("Getting info about user's id: {} bookings", userId);
        isUserExists(userId);
        return bookingFeignClient.getBookingInfo(userId);
    }

    public BookingStatusUpdateDTO updateBookingStatus(long userId, long bookingId, Boolean isConfirmed) {
        isUserExists(userId);
        isUserBookingExists(userId, bookingId);
        isBookingAlreadyUpdatedStatus(userId, bookingId);
        BookingStatusUpdateDTO updateBookingRequest = bookingMapper.toBookingStatusUpdateDTO(userId,
                bookingId, isConfirmed);
        bookingKafkaProducer.sendUpdateBookingStatusToOrchestrator(updateBookingRequest);
        return updateBookingRequest;
    }

    private void isBookingAlreadyUpdatedStatus(long userId, long bookingId) {
        log.info("Check that booking has correct status to change it {}", bookingId);
        ResponseEntity<Set<BookingDTO>> userBookingsResponse = bookingFeignClient.getBookingInfo(userId);
        if (userBookingsResponse.getStatusCode() == HttpStatus.OK && !Objects.requireNonNull(userBookingsResponse.getBody()).isEmpty()) {
            Optional<BookingDTO> maybeBooking = userBookingsResponse.getBody()
                    .stream()
                    .filter(bookingDTO -> bookingDTO.getId() == bookingId).findAny();
            maybeBooking.filter(b ->
                            "AWAITING_CONFIRMATION".equals(b.getBookingStatus()) ||
                                    "AWAITING_PAYMENT".equals(b.getBookingStatus()))
                    .orElseThrow(() -> new BookingStatusException("You can CONFIRM or CANCEL only bookings with status: AWAITING_CONFIRMATION or AWAITING_PAYMENT"));
        } else {
            log.warn("Response from booking-service status: {}", userBookingsResponse.getStatusCode());
            throw new UnexpectedServiceException("Booking service unexpected error");
        }
    }

    private void isUserBookingExists(long userId, long bookingId) {
        log.info("Check that users {} has booking {}", userId, bookingId);
        ResponseEntity<Set<BookingDTO>> userBookingsResponse = bookingFeignClient.getBookingInfo(userId);
        if (userBookingsResponse.getStatusCode() == HttpStatus.OK && !Objects.requireNonNull(userBookingsResponse.getBody()).isEmpty()) {
            Optional<BookingDTO> maybeBooking = userBookingsResponse.getBody()
                    .stream()
                    .filter(bookingDTO -> bookingDTO.getId() == bookingId).findAny();
            if (maybeBooking.isEmpty()) {
                log.warn("User with id: {} doesn't have booking with id: {}", userId, bookingId);
                throw new EntityNotFound("This user: " + userId + " does not have such a booking: " + bookingId + ".");
            }
        } else {
            log.warn("Response from booking-service status: {}", userBookingsResponse.getStatusCode());
            throw new UnexpectedServiceException("Booking service unexpected error");
        }
    }

    private void isUserExists(long userId) {
        log.info("Check that user with id: {} exists", userId);
        try {
            userClient.findUserById(userId);
        } catch (FeignException e) {
            if (e.status() == 400) {
                log.warn("User with id {} not found", userId);
                throw new UnauthorizedExceptionDTO("You must be registered to create new booking. " +
                        "User with id: " + userId + " not found");
            }
        }
    }
}
