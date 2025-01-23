package ru.zubcov.flightbookingservice.apigatewayservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.BookingsApi;
import org.openapitools.model.BookingDTO;
import org.openapitools.model.BookingRequestDTO;
import org.openapitools.model.BookingResponseDTO;
import org.openapitools.model.BookingStatusUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zubcov.flightbookingservice.apigatewayservice.service.BookingService;

import java.util.List;


@Controller
@Validated
@RequiredArgsConstructor
public class GatewayBookingController implements BookingsApi {

    private final BookingService bookingClient;

    @PostMapping("/bookings/{userId}")
    public ResponseEntity<BookingResponseDTO> createBooking(@PathVariable Long userId,
                                                            @RequestBody @Valid BookingRequestDTO request) {
        return new ResponseEntity<>(bookingClient.handleAndSendBookingRequest(request, userId),
                HttpStatus.CREATED);
    }

    @GetMapping("/bookings/{userId}")
    public ResponseEntity<List<BookingDTO>> getUsersBookings(@PathVariable Long userId) {
        return bookingClient.getBookingInfo(userId);
    }

    @PostMapping("/bookings/{bookingId}/users/{userId}/confirm")
    public ResponseEntity<BookingStatusUpdateDTO> updateBookingStatus(@PathVariable Long userId,
                                                                      @PathVariable Long bookingId,
                                                                      @RequestParam(defaultValue = "false") Boolean isConfirmed) {
        return new ResponseEntity<>(bookingClient.updateBookingStatus(userId, bookingId, isConfirmed), HttpStatus.ACCEPTED);
    }

}
