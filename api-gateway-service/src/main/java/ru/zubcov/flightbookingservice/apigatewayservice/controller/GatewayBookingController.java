package ru.zubcov.flightbookingservice.apigatewayservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zubcov.flightbookingservice.apigatewayservice.service.BookingService;
import ru.zubcov.flightbookingservice.commondto.BookingDTO;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingResponseDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;

import java.util.Set;

@Controller
@Validated
@RequiredArgsConstructor
public class GatewayBookingController {

    private final BookingService bookingClient;

    @PostMapping("/users/{userId}/bookings")
    public ResponseEntity<BookingResponseDTO> createBookingRequest(@RequestBody @Valid BookingRequestDTO request,
                                                                   @PathVariable long userId) {
        return new ResponseEntity<>(bookingClient.handleAndSendBookingRequest(request, userId),
                HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<Set<BookingDTO>> getBookingsInfo(@PathVariable long userId) {
        return bookingClient.getBookingInfo(userId);
    }

    @PostMapping("/users/{userId}/bookings/{bookingId}/confirm")
    public ResponseEntity<BookingStatusUpdateDTO> updateBookingStatus(@RequestParam(defaultValue = "false") Boolean isConfirmed,
                                                                      @PathVariable long userId,
                                                                      @PathVariable long bookingId) {
        return new ResponseEntity<>(bookingClient.updateBookingStatus(userId, bookingId, isConfirmed), HttpStatus.ACCEPTED);
    }
}
